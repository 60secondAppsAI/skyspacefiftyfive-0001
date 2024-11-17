package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.LoyaltyProgram;





public interface LoyaltyProgramDAO extends GenericDAO<LoyaltyProgram, Integer> {
  
	List<LoyaltyProgram> findAll();
	






}


