/**
 * @author vinoth
 *
 */
package com.nhance.analytics.service;

import java.util.List;

import com.nhance.analytics.model.Event;

public interface MongoEventService {

	public List<Event> viewEventsData();
	
}
