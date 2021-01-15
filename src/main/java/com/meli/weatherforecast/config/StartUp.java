package com.meli.weatherforecast.config;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.meli.weatherforecast.service.impl.WheatherForecastService;

@Component
public class StartUp {
	
	private WheatherForecastService service;
	
	//Ver forma de ingresar por param. 
	private Integer days = 365;
	
	/**
	 * Javax's @PostConstruct annotation can be used for annotating a method that should be run once immediately after the bean's initialization. 
	 * Keep in mind that the annotated method will be executed by Spring even if there is nothing to inject.
	 */
	@PostConstruct
    public void init() {
		//service.calulateAndPersistForecastForDays(days);
    }

	
}
