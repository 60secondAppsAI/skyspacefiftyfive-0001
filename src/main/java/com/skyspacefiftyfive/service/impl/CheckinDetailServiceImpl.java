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
import com.skyspacefiftyfive.dao.CheckinDetailDAO;
import com.skyspacefiftyfive.domain.CheckinDetail;
import com.skyspacefiftyfive.dto.CheckinDetailDTO;
import com.skyspacefiftyfive.dto.CheckinDetailSearchDTO;
import com.skyspacefiftyfive.dto.CheckinDetailPageDTO;
import com.skyspacefiftyfive.dto.CheckinDetailConvertCriteriaDTO;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;
import com.skyspacefiftyfive.service.CheckinDetailService;
import com.skyspacefiftyfive.util.ControllerUtils;





@Service
public class CheckinDetailServiceImpl extends GenericServiceImpl<CheckinDetail, Integer> implements CheckinDetailService {

    private final static Logger logger = LoggerFactory.getLogger(CheckinDetailServiceImpl.class);

	@Autowired
	CheckinDetailDAO checkinDetailDao;

	


	@Override
	public GenericDAO<CheckinDetail, Integer> getDAO() {
		return (GenericDAO<CheckinDetail, Integer>) checkinDetailDao;
	}
	
	public List<CheckinDetail> findAll () {
		List<CheckinDetail> checkinDetails = checkinDetailDao.findAll();
		
		return checkinDetails;	
		
	}

	public ResultDTO addCheckinDetail(CheckinDetailDTO checkinDetailDTO, RequestDTO requestDTO) {

		CheckinDetail checkinDetail = new CheckinDetail();

		checkinDetail.setCheckinDetailId(checkinDetailDTO.getCheckinDetailId());


		checkinDetail.setCheckinTime(checkinDetailDTO.getCheckinTime());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		checkinDetail = checkinDetailDao.save(checkinDetail);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CheckinDetail> getAllCheckinDetails(Pageable pageable) {
		return checkinDetailDao.findAll(pageable);
	}

	public Page<CheckinDetail> getAllCheckinDetails(Specification<CheckinDetail> spec, Pageable pageable) {
		return checkinDetailDao.findAll(spec, pageable);
	}

	public ResponseEntity<CheckinDetailPageDTO> getCheckinDetails(CheckinDetailSearchDTO checkinDetailSearchDTO) {
	
			Integer checkinDetailId = checkinDetailSearchDTO.getCheckinDetailId(); 
   			String sortBy = checkinDetailSearchDTO.getSortBy();
			String sortOrder = checkinDetailSearchDTO.getSortOrder();
			String searchQuery = checkinDetailSearchDTO.getSearchQuery();
			Integer page = checkinDetailSearchDTO.getPage();
			Integer size = checkinDetailSearchDTO.getSize();

	        Specification<CheckinDetail> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, checkinDetailId, "checkinDetailId"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<CheckinDetail> checkinDetails = this.getAllCheckinDetails(spec, pageable);
		
		//System.out.println(String.valueOf(checkinDetails.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(checkinDetails.getTotalPages()));
		
		List<CheckinDetail> checkinDetailsList = checkinDetails.getContent();
		
		CheckinDetailConvertCriteriaDTO convertCriteria = new CheckinDetailConvertCriteriaDTO();
		List<CheckinDetailDTO> checkinDetailDTOs = this.convertCheckinDetailsToCheckinDetailDTOs(checkinDetailsList,convertCriteria);
		
		CheckinDetailPageDTO checkinDetailPageDTO = new CheckinDetailPageDTO();
		checkinDetailPageDTO.setCheckinDetails(checkinDetailDTOs);
		checkinDetailPageDTO.setTotalElements(checkinDetails.getTotalElements());
		return ResponseEntity.ok(checkinDetailPageDTO);
	}

	public List<CheckinDetailDTO> convertCheckinDetailsToCheckinDetailDTOs(List<CheckinDetail> checkinDetails, CheckinDetailConvertCriteriaDTO convertCriteria) {
		
		List<CheckinDetailDTO> checkinDetailDTOs = new ArrayList<CheckinDetailDTO>();
		
		for (CheckinDetail checkinDetail : checkinDetails) {
			checkinDetailDTOs.add(convertCheckinDetailToCheckinDetailDTO(checkinDetail,convertCriteria));
		}
		
		return checkinDetailDTOs;

	}
	
	public CheckinDetailDTO convertCheckinDetailToCheckinDetailDTO(CheckinDetail checkinDetail, CheckinDetailConvertCriteriaDTO convertCriteria) {
		
		CheckinDetailDTO checkinDetailDTO = new CheckinDetailDTO();
		
		checkinDetailDTO.setCheckinDetailId(checkinDetail.getCheckinDetailId());

	
		checkinDetailDTO.setCheckinTime(checkinDetail.getCheckinTime());

	

		
		return checkinDetailDTO;
	}

	public ResultDTO updateCheckinDetail(CheckinDetailDTO checkinDetailDTO, RequestDTO requestDTO) {
		
		CheckinDetail checkinDetail = checkinDetailDao.getById(checkinDetailDTO.getCheckinDetailId());

		checkinDetail.setCheckinDetailId(ControllerUtils.setValue(checkinDetail.getCheckinDetailId(), checkinDetailDTO.getCheckinDetailId()));

		checkinDetail.setCheckinTime(ControllerUtils.setValue(checkinDetail.getCheckinTime(), checkinDetailDTO.getCheckinTime()));



        checkinDetail = checkinDetailDao.save(checkinDetail);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CheckinDetailDTO getCheckinDetailDTOById(Integer checkinDetailId) {
	
		CheckinDetail checkinDetail = checkinDetailDao.getById(checkinDetailId);
			
		
		CheckinDetailConvertCriteriaDTO convertCriteria = new CheckinDetailConvertCriteriaDTO();
		return(this.convertCheckinDetailToCheckinDetailDTO(checkinDetail,convertCriteria));
	}







}
