package com.meli.weatherforecast.service;

import java.util.List;

import com.meli.weatherforecast.enums.WeatherEnum;
import com.meli.weatherforecast.model.Forecast;

public interface ForecastService {
	
	public Forecast findByDay(int day);
	
	public List<Forecast> findAllByWeather(WeatherEnum weather);
	
	public Double findTopPerimeterFromForecast();
	
	void updateWeatherToHeavyRain(Double perimeter);
	
}
