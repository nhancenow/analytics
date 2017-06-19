/**
 * @author vinoth
 *
 */
package com.nhance.analytics.service;

public interface EventProducer {

	public boolean send(String topicName, String data);
	public void disconnect();
	
}
