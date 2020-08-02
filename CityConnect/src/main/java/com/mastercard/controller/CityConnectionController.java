package com.mastercard.controller;

import com.mastercard.service.CityConnectionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CityConnectionController {

	public static final String YES = "Yes";
	public static final String NO = "No";
	public static final String EXCEPTION_OCCURED_IN_CITY_CONNECT_CONTROLLER_CONNECTED = "Exception occured in CityConnectController.connected";
	@Autowired
	CityConnectionService service;
	private static final Logger logger = LogManager.getLogger(CityConnectionController.class);
	@RequestMapping("/connected")
	public String connected(@RequestParam("origin") String origin, @RequestParam("destination") String destination) throws Exception
	{
		boolean isConnected = false;
		try {
			isConnected = service.isConnected(origin, destination);
		} catch (Exception e) {
			logger.error(EXCEPTION_OCCURED_IN_CITY_CONNECT_CONTROLLER_CONNECTED + e.getMessage());
			throw new Exception(EXCEPTION_OCCURED_IN_CITY_CONNECT_CONTROLLER_CONNECTED);
		}
		return isConnected ? YES : NO;
	}
}
