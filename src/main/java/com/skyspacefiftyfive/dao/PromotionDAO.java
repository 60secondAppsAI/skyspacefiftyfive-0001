package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.Promotion;





public interface PromotionDAO extends GenericDAO<Promotion, Integer> {
  
	List<Promotion> findAll();
	






}


