/**
 * @author vinoth
 *
 */
package com.nhance.analytics.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nhance.analytics.model.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, String>{
	
	 @Query(value = "{ 'eventName' : ?0}")
	 public List<Event> searchByEventName(String eventName);

}
