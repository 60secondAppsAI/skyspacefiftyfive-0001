package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.Flight;





public interface FlightDAO extends GenericDAO<Flight, Integer> {
  
	List<Flight> findAll();
	






}


