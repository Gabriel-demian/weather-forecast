package com.meli.weatherforecast.config;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.meli.weatherforecast.service.impl.WheatherForecastService;

@Component
public class StartUp {
	
	private WheatherForecastService service;
	
	private Integer days;
	
	@PostConstruct
    public void init() {
		//service.calulateAndPersistForecastForDays(days);
    }

	
}
