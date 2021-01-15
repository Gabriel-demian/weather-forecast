package com.meli.weatherforecast.service.impl;

import java.awt.geom.Point2D;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.weatherforecast.model.SolarSystem;
import com.meli.weatherforecast.repository.ForecastRepository;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
public class WheatherForecastService {
	
	@Autowired
	private ForecastRepository forecastRepository;
	
	private SolarSystem solarSystem;
	
	public boolean sunIsInside(int day) {
		
		Point2D positionPlanet1 = solarSystem.getPlanet1Position(day);
		Point2D positionPlanet2 = solarSystem.getPlanet2Position(day);
		Point2D positionPlanet3 = solarSystem.getPlanet3Position(day);
		
		
		
		return true;
	}
	
	
	
}
