/**
 * @author vinoth
 *
 */
package com.nhance.analytics.config;

public class Constants {
	
	public static final String TOPOLOGY_NAME                  	= "EventTopology";
	
	public static final String KAFKA_SPOUT_ID 					= "kafka-spout";
	public static final String EVENT_TYPE_BOLT_ID 				= "event-type-bolt";
	public static final String MONGO_BOLT_ID 					= "mongodb-bolt-id";
	
	public static final String COLLECTION_EVENT               	= "events";
	public static final String MONGODB_STREAM 					= "mongodb-stream";
	public static final String MONGODB_HOST 					= "localhost";
	public static final String MONGODB_DB						= "stream";
	public static final int MONGODB_PORT 						= 27017;
	public static final String EVENT_NAME 						= "Page_Visit";
	
	public static final String ZOOKEEPER_HOST               	= "localhost:2181";
	public static final String KAFKA_TOPIC              		= "test-events";
	public static final String KAFKA_ZKROOT                    	= "/kafka";
	public static final String KAFKA_CONSUMERGROUP     			= "test-consumer-group";
	
}
