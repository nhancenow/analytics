/**
 * @author vinoth
 *
 */
package com.nhance.analytics.storm.bolt;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import com.nhance.analytics.config.Constants;

public class EventTypeBolt extends BaseRichBolt {

	private static final long serialVersionUID = 1L;
	private OutputCollector collector;

	public void execute(Tuple tuple) {
		String value = tuple.getString(0);
		collector.emit(Constants.MONGODB_STREAM, new Values("Event", value));
		System.out.println("<EventTypeBolt> data emit : " + value);
		collector.ack(tuple);
	}

	@SuppressWarnings("rawtypes")
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declareStream(Constants.MONGODB_STREAM, new Fields("Event", "data"));
	}

}
