package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.MealOption;





public interface MealOptionDAO extends GenericDAO<MealOption, Integer> {
  
	List<MealOption> findAll();
	






}


