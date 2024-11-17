package com.skyspacefiftyfive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspacefiftyfive.domain.CustomerSupport;
import com.skyspacefiftyfive.dto.CustomerSupportDTO;
import com.skyspacefiftyfive.dto.CustomerSupportSearchDTO;
import com.skyspacefiftyfive.dto.CustomerSupportPageDTO;
import com.skyspacefiftyfive.dto.CustomerSupportConvertCriteriaDTO;
import com.skyspacefiftyfive.service.GenericService;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CustomerSupportService extends GenericService<CustomerSupport, Integer> {

	List<CustomerSupport> findAll();

	ResultDTO addCustomerSupport(CustomerSupportDTO customerSupportDTO, RequestDTO requestDTO);

	ResultDTO updateCustomerSupport(CustomerSupportDTO customerSupportDTO, RequestDTO requestDTO);

    Page<CustomerSupport> getAllCustomerSupports(Pageable pageable);

    Page<CustomerSupport> getAllCustomerSupports(Specification<CustomerSupport> spec, Pageable pageable);

	ResponseEntity<CustomerSupportPageDTO> getCustomerSupports(CustomerSupportSearchDTO customerSupportSearchDTO);
	
	List<CustomerSupportDTO> convertCustomerSupportsToCustomerSupportDTOs(List<CustomerSupport> customerSupports, CustomerSupportConvertCriteriaDTO convertCriteria);

	CustomerSupportDTO getCustomerSupportDTOById(Integer customerSupportId);







}





