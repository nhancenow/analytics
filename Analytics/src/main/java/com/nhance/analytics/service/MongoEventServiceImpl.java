/**
 * @author vinoth
 *
 */
package com.nhance.analytics.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhance.analytics.config.Constants;
import com.nhance.analytics.model.Event;
import com.nhance.analytics.repository.EventRepository;

@Service("mongoEventService")
public class MongoEventServiceImpl implements MongoEventService {

	Logger LOG = Logger.getLogger(MongoEventServiceImpl.class);
	
	@Autowired
	EventRepository eventRepository;

	public List<Event> viewEventsData() {
		return eventRepository.searchByEventName(Constants.EVENT_NAME);
	}

	
}
