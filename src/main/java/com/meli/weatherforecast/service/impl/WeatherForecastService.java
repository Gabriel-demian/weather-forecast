package com.meli.weatherforecast.service.impl;

import static com.meli.weatherforecast.model.SolarSystem.SUN;

import java.awt.geom.Point2D;
import java.text.DecimalFormat;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.weatherforecast.enums.WeatherEnum;
import com.meli.weatherforecast.geometry.Calculator;
import com.meli.weatherforecast.model.Forecast;
import com.meli.weatherforecast.model.SolarSystem;
import com.meli.weatherforecast.model.WeatherReport;
import com.meli.weatherforecast.service.ForecastService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class WeatherForecastService {

	@Autowired
	private ForecastService forecastService;
	
	private final SolarSystem solarSystem;

	@Transactional
	public void runForecast(int totalDays) {

		log.info("Starting to calculate and persist the forecast for the next " + totalDays + " days.");
		
		for (int currentDay = 0; currentDay < totalDays; currentDay++) {
			
			log.info("Calculating forecast for the day " + currentDay);

			Forecast actualForecast = getForecastByDay(currentDay);		
			
			log.info("The next forecast is going to be saved " + actualForecast.toString());
			
			forecastService.save(actualForecast);

		}
		
		Forecast fore = new Forecast(1, WeatherEnum.OPTIMAL, 123.00);
		Integer y = 12;
		
		log.info("fore 1: " + fore.getPerimeter());
		log.info("y 1: " + y);
		change(fore, y);
		
		log.info("fore 3: " + fore.getPerimeter());
		log.info("y 3: " + y);
		
		updateMaxPerimToHeavyRain();

	}
	
	private void change(Forecast fore, Integer y) {
		fore.setPerimeter(150.00);
		y = 125;
		log.info("fore 2: " + fore.getPerimeter());
		log.info("y 2: " + y);
	}

	private void updateMaxPerimToHeavyRain() {
		Double top = forecastService.findTopPerimeterFromForecast();
		
		forecastService.updateWeatherToHeavyRain(top);
		
		log.info("===> TOP PERIMETER: " + top);
		
	}

	public Forecast getForecastByDay(int day) {
		WeatherEnum weather;
		DecimalFormat df2 = new DecimalFormat("#.####");
		
		if(planetsAligned(day)) {
			log.info("The planets are aligned.");
			
			weather = allignContainsSun(day);
			
			return new Forecast(day, weather, 0.0);
			
		}else {
			
			
			log.info("the planets are forming a triangle.");
			
			log.info("Día " + day + 
					" , pos1: X:"+ df2.format(solarSystem.getPlanet1Position(day).getX()) + "  Y:" +df2.format(solarSystem.getPlanet1Position(day).getY()) + 
					" , pos2: X:"+ df2.format(solarSystem.getPlanet2Position(day).getX()) + "  Y:" +df2.format(solarSystem.getPlanet2Position(day).getY()) + 
					" , pos3: X:"+ df2.format(solarSystem.getPlanet3Position(day).getX()) + "  Y:" +df2.format(solarSystem.getPlanet3Position(day).getY()) );
			
			Double perimeter = Calculator.trianglePerimeter(solarSystem.getPlanet1Position(day), solarSystem.getPlanet2Position(day), solarSystem.getPlanet3Position(day));
			
			weather = triangleContainsSun(day);
			
			return new Forecast(day, weather, perimeter);
		}
	}
	
	public WeatherReport getForecastReport(int totalDays) {
		WeatherReport report = new WeatherReport();
		
		Double maxPerimeter = 0D;
		Integer maxPerimeterDay = null;
		Forecast lastForecast = null;
		
		for(int thisDay = 0; thisDay < totalDays; thisDay++) {
			Forecast currentForecast = forecastService.findByDay(thisDay);
			
			if(thisDay == 0) {
				lastForecast = currentForecast;
				report.addForecast(currentForecast.getWeather());
			}
			
			if(!currentForecast.getWeather().equals(lastForecast.getWeather())) {
				lastForecast = currentForecast;
				report.addForecast(currentForecast.getWeather());
			}
			
			if(WeatherEnum.HEAVY_RAIN.equals(currentForecast.getWeather())) {

                if (currentForecast.getPerimeter() > maxPerimeter) {
                    maxPerimeter = currentForecast.getPerimeter();
                    maxPerimeterDay = thisDay;
                }
            }
			
			
		}
		
		report.setMaxPerimeterDay(maxPerimeterDay);
		
		return report;
	}

	public boolean sunIsInsideTriangle(int day) {

		Point2D positionPlanet1 = solarSystem.getPlanet1Position(day);
		Point2D positionPlanet2 = solarSystem.getPlanet2Position(day);
		Point2D positionPlanet3 = solarSystem.getPlanet3Position(day);

		Double planetsArea = Calculator.areaByHeron(positionPlanet1, positionPlanet2, positionPlanet3);

		double areaWithTheSun1 = Calculator.areaByHeron(SUN, positionPlanet2, positionPlanet3);
		double areaWithTheSun2 = Calculator.areaByHeron(positionPlanet1, SUN, positionPlanet3);
		double areaWithTheSun3 = Calculator.areaByHeron(positionPlanet1, positionPlanet2, SUN);

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
				solarSystem.getPlanet2Position(day), SUN);

		return Calculator.doubleEquals(triangleArea, 0);
	}


	public boolean sunIsAlignedWithPlanets(int day) {
		
		Double triangleArea = Calculator.areaByHeron(SUN, solarSystem.getPlanet2Position(day),
				solarSystem.getPlanet3Position(day));

		return Calculator.doubleEquals(triangleArea, 0);
	}

}
