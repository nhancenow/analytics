/**
 * @author vinoth
 *
 */
package com.nhance.analytics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories({ "com.nhance.analytics.repository" })
public class SpringMongoConfig {
	
    public @Bean
    MongoDbFactory mongoDbFactory() throws Exception {
       return new SimpleMongoDbFactory(new MongoClient(Constants.MONGODB_HOST), Constants.MONGODB_DB);
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {
       MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
       return mongoTemplate;
    }

}