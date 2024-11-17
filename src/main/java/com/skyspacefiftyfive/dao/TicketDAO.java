package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.Ticket;





public interface TicketDAO extends GenericDAO<Ticket, Integer> {
  
	List<Ticket> findAll();
	






}


