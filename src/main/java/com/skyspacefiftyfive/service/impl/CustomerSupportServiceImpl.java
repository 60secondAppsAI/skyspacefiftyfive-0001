package com.skyspacefiftyfive.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skyspacefiftyfive.dao.GenericDAO;
import com.skyspacefiftyfive.service.GenericService;
import com.skyspacefiftyfive.service.impl.GenericServiceImpl;
import com.skyspacefiftyfive.dao.CustomerSupportDAO;
import com.skyspacefiftyfive.domain.CustomerSupport;
import com.skyspacefiftyfive.dto.CustomerSupportDTO;
import com.skyspacefiftyfive.dto.CustomerSupportSearchDTO;
import com.skyspacefiftyfive.dto.CustomerSupportPageDTO;
import com.skyspacefiftyfive.dto.CustomerSupportConvertCriteriaDTO;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;
import com.skyspacefiftyfive.service.CustomerSupportService;
import com.skyspacefiftyfive.util.ControllerUtils;





@Service
public class CustomerSupportServiceImpl extends GenericServiceImpl<CustomerSupport, Integer> implements CustomerSupportService {

    private final static Logger logger = LoggerFactory.getLogger(CustomerSupportServiceImpl.class);

	@Autowired
	CustomerSupportDAO customerSupportDao;

	


	@Override
	public GenericDAO<CustomerSupport, Integer> getDAO() {
		return (GenericDAO<CustomerSupport, Integer>) customerSupportDao;
	}
	
	public List<CustomerSupport> findAll () {
		List<CustomerSupport> customerSupports = customerSupportDao.findAll();
		
		return customerSupports;	
		
	}

	public ResultDTO addCustomerSupport(CustomerSupportDTO customerSupportDTO, RequestDTO requestDTO) {

		CustomerSupport customerSupport = new CustomerSupport();

		customerSupport.setCustomerSupportId(customerSupportDTO.getCustomerSupportId());


		customerSupport.setInquiry(customerSupportDTO.getInquiry());


		customerSupport.setResponse(customerSupportDTO.getResponse());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		customerSupport = customerSupportDao.save(customerSupport);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CustomerSupport> getAllCustomerSupports(Pageable pageable) {
		return customerSupportDao.findAll(pageable);
	}

	public Page<CustomerSupport> getAllCustomerSupports(Specification<CustomerSupport> spec, Pageable pageable) {
		return customerSupportDao.findAll(spec, pageable);
	}

	public ResponseEntity<CustomerSupportPageDTO> getCustomerSupports(CustomerSupportSearchDTO customerSupportSearchDTO) {
	
			Integer customerSupportId = customerSupportSearchDTO.getCustomerSupportId(); 
 			String inquiry = customerSupportSearchDTO.getInquiry(); 
 			String response = customerSupportSearchDTO.getResponse(); 
 			String sortBy = customerSupportSearchDTO.getSortBy();
			String sortOrder = customerSupportSearchDTO.getSortOrder();
			String searchQuery = customerSupportSearchDTO.getSearchQuery();
			Integer page = customerSupportSearchDTO.getPage();
			Integer size = customerSupportSearchDTO.getSize();

	        Specification<CustomerSupport> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, customerSupportId, "customerSupportId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, inquiry, "inquiry"); 
			
			spec = ControllerUtils.andIfNecessary(spec, response, "response"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("inquiry")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("response")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<CustomerSupport> customerSupports = this.getAllCustomerSupports(spec, pageable);
		
		//System.out.println(String.valueOf(customerSupports.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(customerSupports.getTotalPages()));
		
		List<CustomerSupport> customerSupportsList = customerSupports.getContent();
		
		CustomerSupportConvertCriteriaDTO convertCriteria = new CustomerSupportConvertCriteriaDTO();
		List<CustomerSupportDTO> customerSupportDTOs = this.convertCustomerSupportsToCustomerSupportDTOs(customerSupportsList,convertCriteria);
		
		CustomerSupportPageDTO customerSupportPageDTO = new CustomerSupportPageDTO();
		customerSupportPageDTO.setCustomerSupports(customerSupportDTOs);
		customerSupportPageDTO.setTotalElements(customerSupports.getTotalElements());
		return ResponseEntity.ok(customerSupportPageDTO);
	}

	public List<CustomerSupportDTO> convertCustomerSupportsToCustomerSupportDTOs(List<CustomerSupport> customerSupports, CustomerSupportConvertCriteriaDTO convertCriteria) {
		
		List<CustomerSupportDTO> customerSupportDTOs = new ArrayList<CustomerSupportDTO>();
		
		for (CustomerSupport customerSupport : customerSupports) {
			customerSupportDTOs.add(convertCustomerSupportToCustomerSupportDTO(customerSupport,convertCriteria));
		}
		
		return customerSupportDTOs;

	}
	
	public CustomerSupportDTO convertCustomerSupportToCustomerSupportDTO(CustomerSupport customerSupport, CustomerSupportConvertCriteriaDTO convertCriteria) {
		
		CustomerSupportDTO customerSupportDTO = new CustomerSupportDTO();
		
		customerSupportDTO.setCustomerSupportId(customerSupport.getCustomerSupportId());

	
		customerSupportDTO.setInquiry(customerSupport.getInquiry());

	
		customerSupportDTO.setResponse(customerSupport.getResponse());

	

		
		return customerSupportDTO;
	}

	public ResultDTO updateCustomerSupport(CustomerSupportDTO customerSupportDTO, RequestDTO requestDTO) {
		
		CustomerSupport customerSupport = customerSupportDao.getById(customerSupportDTO.getCustomerSupportId());

		customerSupport.setCustomerSupportId(ControllerUtils.setValue(customerSupport.getCustomerSupportId(), customerSupportDTO.getCustomerSupportId()));

		customerSupport.setInquiry(ControllerUtils.setValue(customerSupport.getInquiry(), customerSupportDTO.getInquiry()));

		customerSupport.setResponse(ControllerUtils.setValue(customerSupport.getResponse(), customerSupportDTO.getResponse()));



        customerSupport = customerSupportDao.save(customerSupport);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CustomerSupportDTO getCustomerSupportDTOById(Integer customerSupportId) {
	
		CustomerSupport customerSupport = customerSupportDao.getById(customerSupportId);
			
		
		CustomerSupportConvertCriteriaDTO convertCriteria = new CustomerSupportConvertCriteriaDTO();
		return(this.convertCustomerSupportToCustomerSupportDTO(customerSupport,convertCriteria));
	}







}
