package com.meli.weatherforecast.service.impl;

import java.awt.geom.Point2D;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.weatherforecast.enums.WeatherEnum;
import com.meli.weatherforecast.geometry.Calculator;
import com.meli.weatherforecast.model.SolarSystem;
import com.meli.weatherforecast.repository.ForecastRepository;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
public class WheatherForecastService {
	
	@Autowired
	private ForecastRepository forecastRepository;
	
	private Calculator calculator;
	
	private SolarSystem solarSystem;
	
	public void runForecast(int totalDays) {
		
		log.info("Starting to calculate and persist the forecast for the next " + totalDays + " days.");
		
		for (int currentDay = 0; currentDay < totalDays; currentDay++) {
			
			//crear pronóstico por dia.
					
			forecastRepository.save(null);
			
		}
		
		
	}

	
	
	public boolean sunIsInside(int day) {
		
		Point2D positionPlanet1 = solarSystem.getPlanet1Position(day);
		Point2D positionPlanet2 = solarSystem.getPlanet2Position(day);
		Point2D positionPlanet3 = solarSystem.getPlanet3Position(day);
		
		Double planetsArea = calculator.areaByHeron(positionPlanet1, positionPlanet2, positionPlanet3);
		
		double areaWithTheSun1 = calculator.areaByHeron(SolarSystem.SUN, positionPlanet2, positionPlanet3);
		double areaWithTheSun2 = calculator.areaByHeron(positionPlanet1, SolarSystem.SUN, positionPlanet3);
		double areaWithTheSun3 = calculator.areaByHeron(positionPlanet1, positionPlanet2, SolarSystem.SUN);
		
		Double sumAreasWithTheSun = areaWithTheSun1 + areaWithTheSun2 + areaWithTheSun3;
		
		
		return sumAreasWithTheSun.equals(planetsArea);
	}
	
	public WeatherEnum triangleContainsSun(int day) {
		
		return sunIsInside(day) ? WeatherEnum.RAIN :  WeatherEnum.NONE;
		
	}
	
	public boolean planetsAligned(int day) {
		
		Double triangleArea = calculator.areaByHeron(solarSystem.getPlanet1Position(day), solarSystem.getPlanet2Position(day), solarSystem.getPlanet3Position(day));
		
		return triangleArea.equals(0);
	}
	
	
	
	
	
}






















