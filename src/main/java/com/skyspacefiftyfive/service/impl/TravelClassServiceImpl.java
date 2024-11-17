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
import com.skyspacefiftyfive.dao.TravelClassDAO;
import com.skyspacefiftyfive.domain.TravelClass;
import com.skyspacefiftyfive.dto.TravelClassDTO;
import com.skyspacefiftyfive.dto.TravelClassSearchDTO;
import com.skyspacefiftyfive.dto.TravelClassPageDTO;
import com.skyspacefiftyfive.dto.TravelClassConvertCriteriaDTO;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;
import com.skyspacefiftyfive.service.TravelClassService;
import com.skyspacefiftyfive.util.ControllerUtils;





@Service
public class TravelClassServiceImpl extends GenericServiceImpl<TravelClass, Integer> implements TravelClassService {

    private final static Logger logger = LoggerFactory.getLogger(TravelClassServiceImpl.class);

	@Autowired
	TravelClassDAO travelClassDao;

	


	@Override
	public GenericDAO<TravelClass, Integer> getDAO() {
		return (GenericDAO<TravelClass, Integer>) travelClassDao;
	}
	
	public List<TravelClass> findAll () {
		List<TravelClass> travelClasss = travelClassDao.findAll();
		
		return travelClasss;	
		
	}

	public ResultDTO addTravelClass(TravelClassDTO travelClassDTO, RequestDTO requestDTO) {

		TravelClass travelClass = new TravelClass();

		travelClass.setTravelClassId(travelClassDTO.getTravelClassId());


		travelClass.setName(travelClassDTO.getName());


		travelClass.setAmenitiesIncluded(travelClassDTO.getAmenitiesIncluded());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		travelClass = travelClassDao.save(travelClass);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<TravelClass> getAllTravelClasss(Pageable pageable) {
		return travelClassDao.findAll(pageable);
	}

	public Page<TravelClass> getAllTravelClasss(Specification<TravelClass> spec, Pageable pageable) {
		return travelClassDao.findAll(spec, pageable);
	}

	public ResponseEntity<TravelClassPageDTO> getTravelClasss(TravelClassSearchDTO travelClassSearchDTO) {
	
			Integer travelClassId = travelClassSearchDTO.getTravelClassId(); 
 			String name = travelClassSearchDTO.getName(); 
 			String amenitiesIncluded = travelClassSearchDTO.getAmenitiesIncluded(); 
 			String sortBy = travelClassSearchDTO.getSortBy();
			String sortOrder = travelClassSearchDTO.getSortOrder();
			String searchQuery = travelClassSearchDTO.getSearchQuery();
			Integer page = travelClassSearchDTO.getPage();
			Integer size = travelClassSearchDTO.getSize();

	        Specification<TravelClass> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, travelClassId, "travelClassId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, amenitiesIncluded, "amenitiesIncluded"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("amenitiesIncluded")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<TravelClass> travelClasss = this.getAllTravelClasss(spec, pageable);
		
		//System.out.println(String.valueOf(travelClasss.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(travelClasss.getTotalPages()));
		
		List<TravelClass> travelClasssList = travelClasss.getContent();
		
		TravelClassConvertCriteriaDTO convertCriteria = new TravelClassConvertCriteriaDTO();
		List<TravelClassDTO> travelClassDTOs = this.convertTravelClasssToTravelClassDTOs(travelClasssList,convertCriteria);
		
		TravelClassPageDTO travelClassPageDTO = new TravelClassPageDTO();
		travelClassPageDTO.setTravelClasss(travelClassDTOs);
		travelClassPageDTO.setTotalElements(travelClasss.getTotalElements());
		return ResponseEntity.ok(travelClassPageDTO);
	}

	public List<TravelClassDTO> convertTravelClasssToTravelClassDTOs(List<TravelClass> travelClasss, TravelClassConvertCriteriaDTO convertCriteria) {
		
		List<TravelClassDTO> travelClassDTOs = new ArrayList<TravelClassDTO>();
		
		for (TravelClass travelClass : travelClasss) {
			travelClassDTOs.add(convertTravelClassToTravelClassDTO(travelClass,convertCriteria));
		}
		
		return travelClassDTOs;

	}
	
	public TravelClassDTO convertTravelClassToTravelClassDTO(TravelClass travelClass, TravelClassConvertCriteriaDTO convertCriteria) {
		
		TravelClassDTO travelClassDTO = new TravelClassDTO();
		
		travelClassDTO.setTravelClassId(travelClass.getTravelClassId());

	
		travelClassDTO.setName(travelClass.getName());

	
		travelClassDTO.setAmenitiesIncluded(travelClass.getAmenitiesIncluded());

	

		
		return travelClassDTO;
	}

	public ResultDTO updateTravelClass(TravelClassDTO travelClassDTO, RequestDTO requestDTO) {
		
		TravelClass travelClass = travelClassDao.getById(travelClassDTO.getTravelClassId());

		travelClass.setTravelClassId(ControllerUtils.setValue(travelClass.getTravelClassId(), travelClassDTO.getTravelClassId()));

		travelClass.setName(ControllerUtils.setValue(travelClass.getName(), travelClassDTO.getName()));

		travelClass.setAmenitiesIncluded(ControllerUtils.setValue(travelClass.getAmenitiesIncluded(), travelClassDTO.getAmenitiesIncluded()));



        travelClass = travelClassDao.save(travelClass);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public TravelClassDTO getTravelClassDTOById(Integer travelClassId) {
	
		TravelClass travelClass = travelClassDao.getById(travelClassId);
			
		
		TravelClassConvertCriteriaDTO convertCriteria = new TravelClassConvertCriteriaDTO();
		return(this.convertTravelClassToTravelClassDTO(travelClass,convertCriteria));
	}







}
