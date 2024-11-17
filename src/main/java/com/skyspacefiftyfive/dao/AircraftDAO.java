package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.Aircraft;





public interface AircraftDAO extends GenericDAO<Aircraft, Integer> {
  
	List<Aircraft> findAll();
	






}


