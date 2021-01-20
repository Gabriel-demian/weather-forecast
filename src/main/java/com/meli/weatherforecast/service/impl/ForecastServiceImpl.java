package com.meli.weatherforecast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meli.weatherforecast.enums.WeatherEnum;
import com.meli.weatherforecast.exceptions.NotFoundException;
import com.meli.weatherforecast.model.Forecast;
import com.meli.weatherforecast.repository.ForecastRepository;
import com.meli.weatherforecast.service.ForecastService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
public class ForecastServiceImpl implements ForecastService {
	
	@Autowired
	private ForecastRepository forecastRepository;

	@Override
	public Forecast findByDay(int day) {
		
		Forecast fore = forecastRepository.findByDay(day)
				.orElseThrow(() -> new NotFoundException("The day :" + day + " was not found."));
		
		return fore;
	}

	@Override
	public List<Forecast> findAllByWeather(WeatherEnum weather) {
		
		List<Forecast> forecasts = forecastRepository.findAllByWeather(weather);
		
		if(forecasts.isEmpty()) {
			log.error("The list of forecast is empty");
			throw new NotFoundException("The Database is empty.");
		}
		
		return forecasts;
	}

	@Override
	public Double findTopPerimeterFromForecast() {
		
		return forecastRepository.findTopPerimeterFromForecast();
		
	}

	@Override
	public void updateWeatherToHeavyRain(Double perimeter) {
		
		forecastRepository.updateWeatherToHeavyRain(findTopPerimeterFromForecast());
		
	}

}
