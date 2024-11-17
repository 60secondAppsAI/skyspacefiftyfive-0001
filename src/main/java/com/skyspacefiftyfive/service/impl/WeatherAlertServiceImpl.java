package com.skyspacefiftyfive.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.service.GenericService;
import com.skyspacefiftyfive.service.impl.GenericServiceImpl;
import com.skyspacefiftyfive.dao.WeatherAlertDAO;
import com.skyspacefiftyfive.domain.WeatherAlert;
import com.skyspacefiftyfive.dto.WeatherAlertDTO;
import com.skyspacefiftyfive.dto.WeatherAlertSearchDTO;
import com.skyspacefiftyfive.dto.WeatherAlertPageDTO;
import com.skyspacefiftyfive.dto.WeatherAlertConvertCriteriaDTO;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;
import com.skyspacefiftyfive.service.WeatherAlertService;
import com.skyspacefiftyfive.util.ControllerUtils;





@Service
public class WeatherAlertServiceImpl extends GenericServiceImpl<WeatherAlert, Integer> implements WeatherAlertService {

    private final static Logger logger = LoggerFactory.getLogger(WeatherAlertServiceImpl.class);

	@Autowired
	WeatherAlertDAO weatherAlertDao;

	


	@Override
	public GenericDAO<WeatherAlert, Integer> getDAO() {
		return (GenericDAO<WeatherAlert, Integer>) weatherAlertDao;
	}
	
	public List<WeatherAlert> findAll () {
		List<WeatherAlert> weatherAlerts = weatherAlertDao.findAll();
		
		return weatherAlerts;	
		
	}

	public ResultDTO addWeatherAlert(WeatherAlertDTO weatherAlertDTO, RequestDTO requestDTO) {

		WeatherAlert weatherAlert = new WeatherAlert();

		weatherAlert.setWeatherAlertId(weatherAlertDTO.getWeatherAlertId());


		weatherAlert.setMessage(weatherAlertDTO.getMessage());


		weatherAlert.setAlertTime(weatherAlertDTO.getAlertTime());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		weatherAlert = weatherAlertDao.save(weatherAlert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<WeatherAlert> getAllWeatherAlerts(Pageable pageable) {
		return weatherAlertDao.findAll(pageable);
	}

	public Page<WeatherAlert> getAllWeatherAlerts(Specification<WeatherAlert> spec, Pageable pageable) {
		return weatherAlertDao.findAll(spec, pageable);
	}

	public ResponseEntity<WeatherAlertPageDTO> getWeatherAlerts(WeatherAlertSearchDTO weatherAlertSearchDTO) {
	
			Integer weatherAlertId = weatherAlertSearchDTO.getWeatherAlertId(); 
 			String message = weatherAlertSearchDTO.getMessage(); 
   			String sortBy = weatherAlertSearchDTO.getSortBy();
			String sortOrder = weatherAlertSearchDTO.getSortOrder();
			String searchQuery = weatherAlertSearchDTO.getSearchQuery();
			Integer page = weatherAlertSearchDTO.getPage();
			Integer size = weatherAlertSearchDTO.getSize();

	        Specification<WeatherAlert> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, weatherAlertId, "weatherAlertId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, message, "message"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("message")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<WeatherAlert> weatherAlerts = this.getAllWeatherAlerts(spec, pageable);
		
		//System.out.println(String.valueOf(weatherAlerts.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(weatherAlerts.getTotalPages()));
		
		List<WeatherAlert> weatherAlertsList = weatherAlerts.getContent();
		
		WeatherAlertConvertCriteriaDTO convertCriteria = new WeatherAlertConvertCriteriaDTO();
		List<WeatherAlertDTO> weatherAlertDTOs = this.convertWeatherAlertsToWeatherAlertDTOs(weatherAlertsList,convertCriteria);
		
		WeatherAlertPageDTO weatherAlertPageDTO = new WeatherAlertPageDTO();
		weatherAlertPageDTO.setWeatherAlerts(weatherAlertDTOs);
		weatherAlertPageDTO.setTotalElements(weatherAlerts.getTotalElements());
		return ResponseEntity.ok(weatherAlertPageDTO);
	}

	public List<WeatherAlertDTO> convertWeatherAlertsToWeatherAlertDTOs(List<WeatherAlert> weatherAlerts, WeatherAlertConvertCriteriaDTO convertCriteria) {
		
		List<WeatherAlertDTO> weatherAlertDTOs = new ArrayList<WeatherAlertDTO>();
		
		for (WeatherAlert weatherAlert : weatherAlerts) {
			weatherAlertDTOs.add(convertWeatherAlertToWeatherAlertDTO(weatherAlert,convertCriteria));
		}
		
		return weatherAlertDTOs;

	}
	
	public WeatherAlertDTO convertWeatherAlertToWeatherAlertDTO(WeatherAlert weatherAlert, WeatherAlertConvertCriteriaDTO convertCriteria) {
		
		WeatherAlertDTO weatherAlertDTO = new WeatherAlertDTO();
		
		weatherAlertDTO.setWeatherAlertId(weatherAlert.getWeatherAlertId());

	
		weatherAlertDTO.setMessage(weatherAlert.getMessage());

	
		weatherAlertDTO.setAlertTime(weatherAlert.getAlertTime());

	

		
		return weatherAlertDTO;
	}

	public ResultDTO updateWeatherAlert(WeatherAlertDTO weatherAlertDTO, RequestDTO requestDTO) {
		
		WeatherAlert weatherAlert = weatherAlertDao.getById(weatherAlertDTO.getWeatherAlertId());

		weatherAlert.setWeatherAlertId(ControllerUtils.setValue(weatherAlert.getWeatherAlertId(), weatherAlertDTO.getWeatherAlertId()));

		weatherAlert.setMessage(ControllerUtils.setValue(weatherAlert.getMessage(), weatherAlertDTO.getMessage()));

		weatherAlert.setAlertTime(ControllerUtils.setValue(weatherAlert.getAlertTime(), weatherAlertDTO.getAlertTime()));



        weatherAlert = weatherAlertDao.save(weatherAlert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public WeatherAlertDTO getWeatherAlertDTOById(Integer weatherAlertId) {
	
		WeatherAlert weatherAlert = weatherAlertDao.getById(weatherAlertId);
			
		
		WeatherAlertConvertCriteriaDTO convertCriteria = new WeatherAlertConvertCriteriaDTO();
		return(this.convertWeatherAlertToWeatherAlertDTO(weatherAlert,convertCriteria));
	}







}
