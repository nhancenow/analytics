/**
 * @author vinoth
 *
 */
package com.nhance.analytics.model;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "events")
public class Event {
	@Id
	private String id;
	@Indexed
	private String eventName;
	@Indexed
	@Field("properties")
	private Map<String, Object> properties;
	private Date createdTime = new Date();
	
	public String getId() {
		return id;
	}
	public String getEventName() {
		return eventName;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public Map<String, Object> getProperties() {
		return properties;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", eventName=" + eventName + ", createdTime=" + createdTime + ", properties="
				+ properties + "]";
	}
	
}
