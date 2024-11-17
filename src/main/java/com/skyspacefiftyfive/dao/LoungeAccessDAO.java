package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.LoungeAccess;





public interface LoungeAccessDAO extends GenericDAO<LoungeAccess, Integer> {
  
	List<LoungeAccess> findAll();
	






}


