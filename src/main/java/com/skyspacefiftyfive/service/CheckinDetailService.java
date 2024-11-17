package com.skyspacefiftyfive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspacefiftyfive.domain.CheckinDetail;
import com.skyspacefiftyfive.dto.CheckinDetailDTO;
import com.skyspacefiftyfive.dto.CheckinDetailSearchDTO;
import com.skyspacefiftyfive.dto.CheckinDetailPageDTO;
import com.skyspacefiftyfive.dto.CheckinDetailConvertCriteriaDTO;
import com.skyspacefiftyfive.service.GenericService;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CheckinDetailService extends GenericService<CheckinDetail, Integer> {

	List<CheckinDetail> findAll();

	ResultDTO addCheckinDetail(CheckinDetailDTO checkinDetailDTO, RequestDTO requestDTO);

	ResultDTO updateCheckinDetail(CheckinDetailDTO checkinDetailDTO, RequestDTO requestDTO);

    Page<CheckinDetail> getAllCheckinDetails(Pageable pageable);

    Page<CheckinDetail> getAllCheckinDetails(Specification<CheckinDetail> spec, Pageable pageable);

	ResponseEntity<CheckinDetailPageDTO> getCheckinDetails(CheckinDetailSearchDTO checkinDetailSearchDTO);
	
	List<CheckinDetailDTO> convertCheckinDetailsToCheckinDetailDTOs(List<CheckinDetail> checkinDetails, CheckinDetailConvertCriteriaDTO convertCriteria);

	CheckinDetailDTO getCheckinDetailDTOById(Integer checkinDetailId);







}





