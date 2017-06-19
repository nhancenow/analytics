/**
 * @author vinoth
 *
 */
package com.nhance.analytics.storm.bolt;

import java.io.Serializable;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhance.analytics.config.SpringMongoConfig;
import com.nhance.analytics.model.Event;
import com.nhance.analytics.repository.EventRepository;


public class MongodbBolt extends BaseRichBolt implements Serializable {

	private static final long serialVersionUID = -2235450123751932786L;
	private OutputCollector collector;
	EventRepository eventRepository;
	
	@SuppressWarnings("rawtypes")
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		eventRepository = ctx.getBean(EventRepository.class);
	    ((ConfigurableApplicationContext)ctx).close();
	}
	
	public void execute(Tuple input) {
		try{
			String jsonData = (String) input.getValueByField("data");
			System.out.println("<MongoBolt> data emit : " + jsonData);
			Event eventReq = new ObjectMapper().readValue(jsonData, Event.class);
			eventRepository.save(eventReq);
			collector.ack(input);
		}catch(Exception e) {
			System.out.println("Exception: " + e.getStackTrace());
			collector.fail(input);
		}
	}
	
	@Override
	public void cleanup() {
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
	}
}