package com.ag.restboot.rest;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ag.restboot.services.firstdbcheck.SearchService;

/**
 * An example of creating a Rest api using Spring Annotations @RestController.
 * 
 * 
 * 
 * @author Amol N. Gholap-Predix2.0
 */
@RestController
public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	//@SuppressWarnings("nls")
	@RequestMapping("/")
	public String index(
			@RequestParam(value = "echo", defaultValue = "echo") String echo) {
		return "Greetings from Predix Spring Boot! -Product Config"
				+ (new Date());
	}

	@Autowired
	private SearchService searchService;
	String result = "";

}