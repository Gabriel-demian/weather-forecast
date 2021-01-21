package com.meli.weatherforecast.model;

import java.util.EnumMap;
import java.util.Map;

import com.meli.weatherforecast.enums.WeatherEnum;

import lombok.Data;

@Data
public class WeatherReport {
	
	private Integer maxPerimeterDay;
	
	
	private Map<WeatherEnum, Integer> forecastsPeriods;
    
	public WeatherReport() {
        this.forecastsPeriods = new EnumMap<>(WeatherEnum.class);
        this.maxPerimeterDay = -1;
    }
	
	public void addForecast(WeatherEnum forecast) {
        Integer count = this.forecastsPeriods.getOrDefault(forecast, 0);

        this.forecastsPeriods.put(forecast, count + 1);
    }
	
}
