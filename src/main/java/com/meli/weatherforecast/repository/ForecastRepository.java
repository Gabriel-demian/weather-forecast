package com.meli.weatherforecast.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meli.weatherforecast.enums.WeatherEnum;
import com.meli.weatherforecast.model.Forecast;


public interface ForecastRepository extends JpaRepository<Forecast, Integer>{
	
	Optional<Forecast> findByDay(Integer day);
	
	List<Forecast> findAllByWeather(WeatherEnum weather);
	
	Forecast findTopByOrderByPerimeterGreaterThanDesc();

}
