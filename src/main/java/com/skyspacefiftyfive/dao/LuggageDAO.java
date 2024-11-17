package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.Luggage;





public interface LuggageDAO extends GenericDAO<Luggage, Integer> {
  
	List<Luggage> findAll();
	






}


