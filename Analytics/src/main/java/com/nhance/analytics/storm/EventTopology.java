/**
 * @author vinoth
 *
 */
package com.nhance.analytics.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.StringScheme;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;

import com.nhance.analytics.config.Constants;
import com.nhance.analytics.storm.bolt.EventTypeBolt;
import com.nhance.analytics.storm.bolt.MongodbBolt;

public class EventTopology {

	public static KafkaSpout buildKafkaSpout() {
		BrokerHosts hosts = new ZkHosts(Constants.ZOOKEEPER_HOST);
		SpoutConfig spoutConfig = new SpoutConfig(hosts, Constants.KAFKA_TOPIC, Constants.KAFKA_ZKROOT, Constants.KAFKA_CONSUMERGROUP);
		spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
		spoutConfig.startOffsetTime = kafka.api.OffsetRequest.LatestTime();
		KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);
		return kafkaSpout;
	}

	public static void main(String[] args) throws Exception {
		
		// building storm topology - Events
		TopologyBuilder builder = new TopologyBuilder();
		
		//reading from topic using kafka spout
		builder.setSpout(Constants.KAFKA_SPOUT_ID, buildKafkaSpout(), 1);
		
		//event type bolt
		builder.setBolt(Constants.EVENT_TYPE_BOLT_ID, new EventTypeBolt(), 1).shuffleGrouping(Constants.KAFKA_SPOUT_ID);
		
		//mongo bolt for storing events
		builder.setBolt(Constants.MONGO_BOLT_ID, new MongodbBolt(), 1).shuffleGrouping(Constants.EVENT_TYPE_BOLT_ID,
				Constants.MONGODB_STREAM);

		Config conf = new Config();
		conf.setDebug(true);
		conf.setNumWorkers(1);
		System.setProperty("storm.jar", "C:/apache-storm-1.1.0/lib/storm-core-1.1.0.jar");
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology(Constants.TOPOLOGY_NAME, conf, builder.createTopology());
	}
}
