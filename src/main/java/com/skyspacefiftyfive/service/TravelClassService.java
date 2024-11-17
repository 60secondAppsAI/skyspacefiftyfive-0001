package com.skyspacefiftyfive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspacefiftyfive.domain.TravelClass;
import com.skyspacefiftyfive.dto.TravelClassDTO;
import com.skyspacefiftyfive.dto.TravelClassSearchDTO;
import com.skyspacefiftyfive.dto.TravelClassPageDTO;
import com.skyspacefiftyfive.dto.TravelClassConvertCriteriaDTO;
import com.skyspacefiftyfive.service.GenericService;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TravelClassService extends GenericService<TravelClass, Integer> {

	List<TravelClass> findAll();

	ResultDTO addTravelClass(TravelClassDTO travelClassDTO, RequestDTO requestDTO);

	ResultDTO updateTravelClass(TravelClassDTO travelClassDTO, RequestDTO requestDTO);

    Page<TravelClass> getAllTravelClasss(Pageable pageable);

    Page<TravelClass> getAllTravelClasss(Specification<TravelClass> spec, Pageable pageable);

	ResponseEntity<TravelClassPageDTO> getTravelClasss(TravelClassSearchDTO travelClassSearchDTO);
	
	List<TravelClassDTO> convertTravelClasssToTravelClassDTOs(List<TravelClass> travelClasss, TravelClassConvertCriteriaDTO convertCriteria);

	TravelClassDTO getTravelClassDTOById(Integer travelClassId);







}





