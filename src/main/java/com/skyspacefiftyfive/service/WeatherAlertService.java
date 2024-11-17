package com.skyspacefiftyfive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspacefiftyfive.domain.WeatherAlert;
import com.skyspacefiftyfive.dto.WeatherAlertDTO;
import com.skyspacefiftyfive.dto.WeatherAlertSearchDTO;
import com.skyspacefiftyfive.dto.WeatherAlertPageDTO;
import com.skyspacefiftyfive.dto.WeatherAlertConvertCriteriaDTO;
import com.skyspacefiftyfive.service.GenericService;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface WeatherAlertService extends GenericService<WeatherAlert, Integer> {

	List<WeatherAlert> findAll();

	ResultDTO addWeatherAlert(WeatherAlertDTO weatherAlertDTO, RequestDTO requestDTO);

	ResultDTO updateWeatherAlert(WeatherAlertDTO weatherAlertDTO, RequestDTO requestDTO);

    Page<WeatherAlert> getAllWeatherAlerts(Pageable pageable);

    Page<WeatherAlert> getAllWeatherAlerts(Specification<WeatherAlert> spec, Pageable pageable);

	ResponseEntity<WeatherAlertPageDTO> getWeatherAlerts(WeatherAlertSearchDTO weatherAlertSearchDTO);
	
	List<WeatherAlertDTO> convertWeatherAlertsToWeatherAlertDTOs(List<WeatherAlert> weatherAlerts, WeatherAlertConvertCriteriaDTO convertCriteria);

	WeatherAlertDTO getWeatherAlertDTOById(Integer weatherAlertId);







}





