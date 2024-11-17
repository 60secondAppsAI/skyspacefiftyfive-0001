package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.WeatherAlert;





public interface WeatherAlertDAO extends GenericDAO<WeatherAlert, Integer> {
  
	List<WeatherAlert> findAll();
	






}


