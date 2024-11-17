package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.SeatMap;





public interface SeatMapDAO extends GenericDAO<SeatMap, Integer> {
  
	List<SeatMap> findAll();
	






}


