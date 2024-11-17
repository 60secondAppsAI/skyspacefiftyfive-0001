package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.Airport;





public interface AirportDAO extends GenericDAO<Airport, Integer> {
  
	List<Airport> findAll();
	






}


