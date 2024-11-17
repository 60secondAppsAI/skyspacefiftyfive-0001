package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.CheckinDetail;





public interface CheckinDetailDAO extends GenericDAO<CheckinDetail, Integer> {
  
	List<CheckinDetail> findAll();
	






}


