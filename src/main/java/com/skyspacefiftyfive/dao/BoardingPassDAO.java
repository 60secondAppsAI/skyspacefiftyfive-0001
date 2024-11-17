package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.BoardingPass;





public interface BoardingPassDAO extends GenericDAO<BoardingPass, Integer> {
  
	List<BoardingPass> findAll();
	






}


