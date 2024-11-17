package com.skyspacefiftyfive.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.skyspacefiftyfive.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.skyspacefiftyfive.domain.WeatherAlert;
import com.skyspacefiftyfive.dto.WeatherAlertDTO;
import com.skyspacefiftyfive.dto.WeatherAlertSearchDTO;
import com.skyspacefiftyfive.dto.WeatherAlertPageDTO;
import com.skyspacefiftyfive.service.WeatherAlertService;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/weatherAlert")
@RestController
public class WeatherAlertController {

	private final static Logger logger = LoggerFactory.getLogger(WeatherAlertController.class);

	@Autowired
	WeatherAlertService weatherAlertService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<WeatherAlert> getAll() {

		List<WeatherAlert> weatherAlerts = weatherAlertService.findAll();
		
		return weatherAlerts;	
	}

	@GetMapping(value = "/{weatherAlertId}")
	@ResponseBody
	public WeatherAlertDTO getWeatherAlert(@PathVariable Integer weatherAlertId) {
		
		return (weatherAlertService.getWeatherAlertDTOById(weatherAlertId));
	}

 	@RequestMapping(value = "/addWeatherAlert", method = RequestMethod.POST)
	public ResponseEntity<?> addWeatherAlert(@RequestBody WeatherAlertDTO weatherAlertDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = weatherAlertService.addWeatherAlert(weatherAlertDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/weatherAlerts")
	public ResponseEntity<WeatherAlertPageDTO> getWeatherAlerts(WeatherAlertSearchDTO weatherAlertSearchDTO) {
 
		return weatherAlertService.getWeatherAlerts(weatherAlertSearchDTO);
	}	

	@RequestMapping(value = "/updateWeatherAlert", method = RequestMethod.POST)
	public ResponseEntity<?> updateWeatherAlert(@RequestBody WeatherAlertDTO weatherAlertDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = weatherAlertService.updateWeatherAlert(weatherAlertDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
