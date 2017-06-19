/**
 * @author vinoth
 *
 */
package com.nhance.analytics.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "events")
public class Event {
	@Id
	private String id;
	private String eventName;
	private String eventCategory;
	private String pageId;
	private String userName;
	private String emailId;
	private String mobile;
	private String ipAddress;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public String getEventCategory() {
		return eventCategory;
	}
	public String getPageId() {
		return pageId;
	}
	public String getUserName() {
		return userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public String getMobile() {
		return mobile;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	@Override
	public String toString() {
		return "ServiceEventRequest [id=" + id + ", eventName=" + eventName + ", eventCategory=" + eventCategory
				+ ", pageId=" + pageId + ", userName=" + userName + ", emailId=" + emailId + ", mobile=" + mobile
				+ ", ipAddress=" + ipAddress + "]";
	}
	
}
