package com.skyspacefiftyfive.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class WeatherAlertDTO {

	private Integer weatherAlertId;

	private String message;

	private Date alertTime;






}
