package com.skyspacefiftyfive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspacefiftyfive.domain.PaymentDetail;
import com.skyspacefiftyfive.dto.PaymentDetailDTO;
import com.skyspacefiftyfive.dto.PaymentDetailSearchDTO;
import com.skyspacefiftyfive.dto.PaymentDetailPageDTO;
import com.skyspacefiftyfive.dto.PaymentDetailConvertCriteriaDTO;
import com.skyspacefiftyfive.service.GenericService;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PaymentDetailService extends GenericService<PaymentDetail, Integer> {

	List<PaymentDetail> findAll();

	ResultDTO addPaymentDetail(PaymentDetailDTO paymentDetailDTO, RequestDTO requestDTO);

	ResultDTO updatePaymentDetail(PaymentDetailDTO paymentDetailDTO, RequestDTO requestDTO);

    Page<PaymentDetail> getAllPaymentDetails(Pageable pageable);

    Page<PaymentDetail> getAllPaymentDetails(Specification<PaymentDetail> spec, Pageable pageable);

	ResponseEntity<PaymentDetailPageDTO> getPaymentDetails(PaymentDetailSearchDTO paymentDetailSearchDTO);
	
	List<PaymentDetailDTO> convertPaymentDetailsToPaymentDetailDTOs(List<PaymentDetail> paymentDetails, PaymentDetailConvertCriteriaDTO convertCriteria);

	PaymentDetailDTO getPaymentDetailDTOById(Integer paymentDetailId);







}





