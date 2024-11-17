package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.CrewMember;





public interface CrewMemberDAO extends GenericDAO<CrewMember, Integer> {
  
	List<CrewMember> findAll();
	






}


