package com.meli.weatherforecast.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meli.weatherforecast.service.impl.WeatherForecastService;

@Component
public class StartUp {
	
	@Autowired
	private WeatherForecastService service;
	
	//Ver forma de ingresar por param. 
	private Integer days = 3650;
	
	/**
	 * Javax's @PostConstruct annotation can be used for annotating a method that should be run once immediately after the bean's initialization. 
	 * Keep in mind that the annotated method will be executed by Spring even if there is nothing to inject.
	 */
	@PostConstruct
    public void init() {
		service.runForecast(days);
    }

	public Integer getDays() {
		return days;
	}
	
	
}
