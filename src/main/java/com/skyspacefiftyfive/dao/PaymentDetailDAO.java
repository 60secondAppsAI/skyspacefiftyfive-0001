package com.skyspacefiftyfive.dao;

import java.util.List;

import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.domain.PaymentDetail;





public interface PaymentDetailDAO extends GenericDAO<PaymentDetail, Integer> {
  
	List<PaymentDetail> findAll();
	






}


