package com.meli.weatherforecast.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meli.weatherforecast.enums.WeatherEnum;

import lombok.Data;

@Entity
@Table
@Data
public class Forecast {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	@Column
	private Integer day;
	
	@Column
	@Enumerated(EnumType.STRING)
	private WeatherEnum weather;
	
	
	
}
