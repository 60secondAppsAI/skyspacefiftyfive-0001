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
import com.skyspacefiftyfive.dao.SeatMapDAO;
import com.skyspacefiftyfive.domain.SeatMap;
import com.skyspacefiftyfive.dto.SeatMapDTO;
import com.skyspacefiftyfive.dto.SeatMapSearchDTO;
import com.skyspacefiftyfive.dto.SeatMapPageDTO;
import com.skyspacefiftyfive.dto.SeatMapConvertCriteriaDTO;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;
import com.skyspacefiftyfive.service.SeatMapService;
import com.skyspacefiftyfive.util.ControllerUtils;





@Service
public class SeatMapServiceImpl extends GenericServiceImpl<SeatMap, Integer> implements SeatMapService {

    private final static Logger logger = LoggerFactory.getLogger(SeatMapServiceImpl.class);

	@Autowired
	SeatMapDAO seatMapDao;

	


	@Override
	public GenericDAO<SeatMap, Integer> getDAO() {
		return (GenericDAO<SeatMap, Integer>) seatMapDao;
	}
	
	public List<SeatMap> findAll () {
		List<SeatMap> seatMaps = seatMapDao.findAll();
		
		return seatMaps;	
		
	}

	public ResultDTO addSeatMap(SeatMapDTO seatMapDTO, RequestDTO requestDTO) {

		SeatMap seatMap = new SeatMap();

		seatMap.setSeatMapId(seatMapDTO.getSeatMapId());


		seatMap.setConfiguration(seatMapDTO.getConfiguration());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		seatMap = seatMapDao.save(seatMap);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<SeatMap> getAllSeatMaps(Pageable pageable) {
		return seatMapDao.findAll(pageable);
	}

	public Page<SeatMap> getAllSeatMaps(Specification<SeatMap> spec, Pageable pageable) {
		return seatMapDao.findAll(spec, pageable);
	}

	public ResponseEntity<SeatMapPageDTO> getSeatMaps(SeatMapSearchDTO seatMapSearchDTO) {
	
			Integer seatMapId = seatMapSearchDTO.getSeatMapId(); 
 			String configuration = seatMapSearchDTO.getConfiguration(); 
 			String sortBy = seatMapSearchDTO.getSortBy();
			String sortOrder = seatMapSearchDTO.getSortOrder();
			String searchQuery = seatMapSearchDTO.getSearchQuery();
			Integer page = seatMapSearchDTO.getPage();
			Integer size = seatMapSearchDTO.getSize();

	        Specification<SeatMap> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, seatMapId, "seatMapId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, configuration, "configuration"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("configuration")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<SeatMap> seatMaps = this.getAllSeatMaps(spec, pageable);
		
		//System.out.println(String.valueOf(seatMaps.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(seatMaps.getTotalPages()));
		
		List<SeatMap> seatMapsList = seatMaps.getContent();
		
		SeatMapConvertCriteriaDTO convertCriteria = new SeatMapConvertCriteriaDTO();
		List<SeatMapDTO> seatMapDTOs = this.convertSeatMapsToSeatMapDTOs(seatMapsList,convertCriteria);
		
		SeatMapPageDTO seatMapPageDTO = new SeatMapPageDTO();
		seatMapPageDTO.setSeatMaps(seatMapDTOs);
		seatMapPageDTO.setTotalElements(seatMaps.getTotalElements());
		return ResponseEntity.ok(seatMapPageDTO);
	}

	public List<SeatMapDTO> convertSeatMapsToSeatMapDTOs(List<SeatMap> seatMaps, SeatMapConvertCriteriaDTO convertCriteria) {
		
		List<SeatMapDTO> seatMapDTOs = new ArrayList<SeatMapDTO>();
		
		for (SeatMap seatMap : seatMaps) {
			seatMapDTOs.add(convertSeatMapToSeatMapDTO(seatMap,convertCriteria));
		}
		
		return seatMapDTOs;

	}
	
	public SeatMapDTO convertSeatMapToSeatMapDTO(SeatMap seatMap, SeatMapConvertCriteriaDTO convertCriteria) {
		
		SeatMapDTO seatMapDTO = new SeatMapDTO();
		
		seatMapDTO.setSeatMapId(seatMap.getSeatMapId());

	
		seatMapDTO.setConfiguration(seatMap.getConfiguration());

	

		
		return seatMapDTO;
	}

	public ResultDTO updateSeatMap(SeatMapDTO seatMapDTO, RequestDTO requestDTO) {
		
		SeatMap seatMap = seatMapDao.getById(seatMapDTO.getSeatMapId());

		seatMap.setSeatMapId(ControllerUtils.setValue(seatMap.getSeatMapId(), seatMapDTO.getSeatMapId()));

		seatMap.setConfiguration(ControllerUtils.setValue(seatMap.getConfiguration(), seatMapDTO.getConfiguration()));



        seatMap = seatMapDao.save(seatMap);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SeatMapDTO getSeatMapDTOById(Integer seatMapId) {
	
		SeatMap seatMap = seatMapDao.getById(seatMapId);
			
		
		SeatMapConvertCriteriaDTO convertCriteria = new SeatMapConvertCriteriaDTO();
		return(this.convertSeatMapToSeatMapDTO(seatMap,convertCriteria));
	}







}
