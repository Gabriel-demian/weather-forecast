package com.meli.weatherforecast.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.meli.weatherforecast.enums.WeatherEnum;
import com.meli.weatherforecast.model.Forecast;


public interface ForecastRepository extends JpaRepository<Forecast, Integer>{
	
	public final static String GET_MAX_PERIMETER = "SELECT MAX(perimeter) FROM Forecast ";
	
	public final static String UPDATE_WEATHER = "UPDATE Forecast SET weather = 'HEAVY RAIN' WHERE perimeter = :perimeter";
	
	Optional<Forecast> findByDay(Integer day);
	
	List<Forecast> findAllByWeather(WeatherEnum weather);
	
	@Query(GET_MAX_PERIMETER)
	Double findTopPerimeterFromForecast();
	
	@Modifying
	@Query(UPDATE_WEATHER)
	void updateWeatherToHeavyRain(@Param("perimeter")final Double perimeter);
	
}
