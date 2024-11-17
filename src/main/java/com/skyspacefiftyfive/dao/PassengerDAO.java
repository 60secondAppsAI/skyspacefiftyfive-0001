package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.Passenger;





public interface PassengerDAO extends GenericDAO<Passenger, Integer> {
  
	List<Passenger> findAll();
	






}


