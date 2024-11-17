package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.CustomerSupport;





public interface CustomerSupportDAO extends GenericDAO<CustomerSupport, Integer> {
  
	List<CustomerSupport> findAll();
	






}


