/**
 * @author vinoth
 *
 */
package com.nhance.analytics.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.nhance.analytics.config.SpringMongoConfig;
import com.nhance.analytics.model.Event;

public class Main {

	public static void main(String[] args) {
		// For Annotation
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		((ConfigurableApplicationContext)ctx).close();

		 // find
		 Query findQuery = new Query();
		 findQuery.addCriteria(Criteria.where("eventName").is("Page_Visit"));
		 Event retrievedFirstSurvey = mongoOperation.findOne(findQuery, Event.class);
		 System.out.println(retrievedFirstSurvey);
	}
}
