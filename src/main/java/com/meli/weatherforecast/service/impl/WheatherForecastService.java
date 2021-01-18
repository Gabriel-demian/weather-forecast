package com.meli.weatherforecast.service.impl;

import java.awt.geom.Point2D;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.weatherforecast.enums.WeatherEnum;
import com.meli.weatherforecast.geometry.Calculator;
import com.meli.weatherforecast.model.Forecast;
import com.meli.weatherforecast.model.SolarSystem;
import com.meli.weatherforecast.repository.ForecastRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class WheatherForecastService {

	@Autowired
	private ForecastRepository forecastRepository;
	

	private final SolarSystem solarSystem;

	public void runForecast(int totalDays) {

		log.info("Starting to calculate and persist the forecast for the next " + totalDays + " days.");

		for (int currentDay = 0; currentDay < totalDays; currentDay++) {
			
			log.info("Calculating forecast for the day " + currentDay);

			Forecast actualForecast = getForecastByDay(currentDay);		
			
			log.info("The next forecast is going to be saved " + actualForecast.toString());
			
			forecastRepository.save(actualForecast);

		}

	}

	public Forecast getForecastByDay(int day) {
		WeatherEnum weather;
		
		if(planetsAligned(day)) {
			log.info("The planets are aligned.");
			
			weather = allignContainsSun(day);
			
			return new Forecast(day, weather, 0.0);
			
		}else {
			log.info("the planets are forming a triangle.");
			
			Double perimeter = Calculator.areaByHeron(solarSystem.getPlanet1Position(day), solarSystem.getPlanet2Position(day), solarSystem.getPlanet3Position(day));
			
			weather = triangleContainsSun(day);
			
			return new Forecast(day, weather, perimeter);
		}
	}

	public boolean sunIsInsideTriangle(int day) {

		Point2D positionPlanet1 = solarSystem.getPlanet1Position(day);
		Point2D positionPlanet2 = solarSystem.getPlanet2Position(day);
		Point2D positionPlanet3 = solarSystem.getPlanet3Position(day);

		Double planetsArea = Calculator.areaByHeron(positionPlanet1, positionPlanet2, positionPlanet3);

		double areaWithTheSun1 = Calculator.areaByHeron(SolarSystem.SUN, positionPlanet2, positionPlanet3);
		double areaWithTheSun2 = Calculator.areaByHeron(positionPlanet1, SolarSystem.SUN, positionPlanet3);
		double areaWithTheSun3 = Calculator.areaByHeron(positionPlanet1, positionPlanet2, SolarSystem.SUN);

		Double sumAreasWithTheSun = areaWithTheSun1 + areaWithTheSun2 + areaWithTheSun3;

		return Calculator.doubleEquals(sumAreasWithTheSun, planetsArea);
	}

	public WeatherEnum triangleContainsSun(int day) {

		return sunIsInsideTriangle(day) ? WeatherEnum.RAIN : WeatherEnum.NONE;

	}
	
	public WeatherEnum allignContainsSun(int day) {

		return sunIsAligned(day) ? WeatherEnum.DROUGHT : WeatherEnum.OPTIMAL;

	}

	public boolean planetsAligned(int day) {

		Double triangleArea = Calculator.areaByHeron(solarSystem.getPlanet1Position(day),
				solarSystem.getPlanet2Position(day), solarSystem.getPlanet3Position(day));

		return Calculator.doubleEquals(triangleArea, 0);
	}
	
	public boolean sunIsAligned(int day) {

		Double triangleArea = Calculator.areaByHeron(solarSystem.getPlanet1Position(day),
				solarSystem.getPlanet2Position(day), solarSystem.SUN);

		return Calculator.doubleEquals(triangleArea, 0);
	}


	public boolean sunIsAlignedWithPlanets(int day) {
		
		Double triangleArea = Calculator.areaByHeron(SolarSystem.SUN, solarSystem.getPlanet2Position(day),
				solarSystem.getPlanet3Position(day));

		return Calculator.doubleEquals(triangleArea, 0);
	}

}
