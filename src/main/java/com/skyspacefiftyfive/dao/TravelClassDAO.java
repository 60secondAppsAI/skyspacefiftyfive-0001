package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.TravelClass;





public interface TravelClassDAO extends GenericDAO<TravelClass, Integer> {
  
	List<TravelClass> findAll();
	






}


