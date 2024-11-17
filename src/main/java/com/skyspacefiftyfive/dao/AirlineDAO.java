package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.Airline;





public interface AirlineDAO extends GenericDAO<Airline, Integer> {
  
	List<Airline> findAll();
	






}


