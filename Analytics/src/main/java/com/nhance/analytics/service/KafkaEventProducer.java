/**
 * @author vinoth
 *
 */
package com.nhance.analytics.service;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.nhance.analytics.service.EventProducer;

@Service("eventProducer")
public class KafkaEventProducer implements EventProducer {

	Logger LOG = Logger.getLogger(KafkaEventProducer.class);

	private static Producer<String, String> producer;

	static {
		synchronized (KafkaEventProducer.class) {
			if (producer == null) {
				Properties props = new Properties();
				props.put("zk.connect", "127.0.0.1:2181");
				props.put("metadata.broker.list", "127.0.0.1:9092");
				props.put("bootstrap.servers", "127.0.0.1:9092");
				props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
			    props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
				producer = new KafkaProducer<>(props);
			}
		}
	}

	@Override
	public boolean send(String topicName, String msg) {
		boolean success = false;
		try {
			producer.send(new ProducerRecord<String, String>(topicName, msg));
			LOG.info("<Producer> Message sent to Kafka broker().....");
			LOG.info("<Message> "+msg);
		} catch (Exception e) {
			success = false;
			LOG.error("Error while sending kafka message: " + e.getStackTrace());
		}
		return success;
	}

	@Override
	public void disconnect() {
		producer.close();
	}

}
