package com.meli.weatherforecast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.meli.weatherforecast.model.Forecast;
import com.meli.weatherforecast.service.ForecastService;
import com.meli.weatherforecast.service.impl.WeatherForecastService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
public class WeatherForecastController {
	
	@Autowired
	ForecastService forecastService;
	
	@Autowired
	WeatherForecastService weatherForecastService;
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value= "/forecast")
	public ResponseEntity weatherForecast(@RequestParam(value= "day") int day) {
		
		log.info("Starting to get the forecast for day " + day);
		
		if(day < 0) {
			return ResponseEntity.badRequest().body(ImmutableMap.of("Message", "The 'day' parameter must be greater than zero"));
		}
		
		Forecast forecast = forecastService.findByDay(day);
		
		return ResponseEntity.ok().body(forecast);
		
	}
	
	
	
}
