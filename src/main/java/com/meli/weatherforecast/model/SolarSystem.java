package com.meli.weatherforecast.model;

import java.awt.geom.Point2D;

import lombok.Data;

@Data
public class SolarSystem {
	
	/**
	 * The solar system only have 3 planets. And The sun will be at the origin (0,0)
	 */
	
	public static final Point2D.Double SUN = new Point2D.Double(0.0, 0.0);

    private Planet planet1;
    private Planet planet2;
    private Planet planet3;
    
	public SolarSystem(Planet planet1, Planet planet2, Planet planet3) {
		this.planet1 = planet1;
		this.planet2 = planet2;
		this.planet3 = planet3;
	}
	
	public Point2D getPlanet1Position(int day) {
		return this.planet2.getPosition(day);
	}
	
	public Point2D getPlanet2Position(int day) {
		return this.planet1.getPosition(day);
	}
	
	public Point2D getPlanet3Position(int day) {
		return this.planet3.getPosition(day);
	}
    
}
