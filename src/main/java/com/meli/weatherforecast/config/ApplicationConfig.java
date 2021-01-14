package com.meli.weatherforecast.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.meli.weatherforecast.model.Planet;
import com.meli.weatherforecast.model.SolarSystem;

@Configuration
public class ApplicationConfig {
	
	@Qualifier(value = "allSolarSystem")
    @Bean
    public SolarSystem allSolarSystem() {
		
        Planet ferengi = new Planet(1, true, 500);
        Planet betasoide = new Planet(3, true, 2000);
        Planet vulkano = new Planet(5, false, 1000);
        
        return new SolarSystem(ferengi, betasoide, vulkano);
    }
	
}
