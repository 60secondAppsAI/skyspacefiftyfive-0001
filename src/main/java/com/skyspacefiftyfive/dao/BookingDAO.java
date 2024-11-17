package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.Booking;





public interface BookingDAO extends GenericDAO<Booking, Integer> {
  
	List<Booking> findAll();
	






}


