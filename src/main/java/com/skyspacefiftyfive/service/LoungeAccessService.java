package com.skyspacefiftyfive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspacefiftyfive.domain.LoungeAccess;
import com.skyspacefiftyfive.dto.LoungeAccessDTO;
import com.skyspacefiftyfive.dto.LoungeAccessSearchDTO;
import com.skyspacefiftyfive.dto.LoungeAccessPageDTO;
import com.skyspacefiftyfive.dto.LoungeAccessConvertCriteriaDTO;
import com.skyspacefiftyfive.service.GenericService;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface LoungeAccessService extends GenericService<LoungeAccess, Integer> {

	List<LoungeAccess> findAll();

	ResultDTO addLoungeAccess(LoungeAccessDTO loungeAccessDTO, RequestDTO requestDTO);

	ResultDTO updateLoungeAccess(LoungeAccessDTO loungeAccessDTO, RequestDTO requestDTO);

    Page<LoungeAccess> getAllLoungeAccesss(Pageable pageable);

    Page<LoungeAccess> getAllLoungeAccesss(Specification<LoungeAccess> spec, Pageable pageable);

	ResponseEntity<LoungeAccessPageDTO> getLoungeAccesss(LoungeAccessSearchDTO loungeAccessSearchDTO);
	
	List<LoungeAccessDTO> convertLoungeAccesssToLoungeAccessDTOs(List<LoungeAccess> loungeAccesss, LoungeAccessConvertCriteriaDTO convertCriteria);

	LoungeAccessDTO getLoungeAccessDTOById(Integer loungeAccessId);







}





