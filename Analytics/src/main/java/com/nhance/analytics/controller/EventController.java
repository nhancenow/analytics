/**
 * @author vinoth
 *
 */
package com.nhance.analytics.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nhance.analytics.config.Keys;
import com.nhance.analytics.model.Event;
import com.nhance.analytics.service.EventProducer;
import com.nhance.analytics.service.MongoEventService;

@Controller
public class EventController {

	Logger LOG = Logger.getLogger(EventController.class);
	private static final String VIEW_INDEX = "index";
	private static final String VIEW_DATA = "allData";

	@Autowired
	private EventProducer eventProducer;

	@Autowired
	private MongoEventService mongoEventService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap model) {
		return VIEW_INDEX;
	}

	@ResponseBody
	@RequestMapping(value = "/send", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String sendEvent(@RequestBody Event event) {
		try {
			LOG.info("<Controller> Json input : " + event.toString());
			ObjectWriter ow = new ObjectMapper().writer();

			// Event producer
			eventProducer.send(Keys.KAFKA_TOPIC, ow.writeValueAsString(event));

		} catch (IOException e) {
			LOG.error("Invalid request:" + e.getStackTrace());
			return "Error";
		}
		return "success";
	}

	@RequestMapping(value = "/dashboard/all", method = RequestMethod.GET)
	public String allData(ModelMap model) {
		try {
			model.addAttribute("eventList", mongoEventService.viewEventsData());
		} catch (Exception e) {
			LOG.error("Exception : " + e.getStackTrace());
			model.addAttribute("message", "Invalid request");
			return VIEW_DATA;
		}
		return VIEW_DATA;
	}

}
