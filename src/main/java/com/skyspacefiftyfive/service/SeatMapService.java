package com.skyspacefiftyfive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspacefiftyfive.domain.SeatMap;
import com.skyspacefiftyfive.dto.SeatMapDTO;
import com.skyspacefiftyfive.dto.SeatMapSearchDTO;
import com.skyspacefiftyfive.dto.SeatMapPageDTO;
import com.skyspacefiftyfive.dto.SeatMapConvertCriteriaDTO;
import com.skyspacefiftyfive.service.GenericService;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SeatMapService extends GenericService<SeatMap, Integer> {

	List<SeatMap> findAll();

	ResultDTO addSeatMap(SeatMapDTO seatMapDTO, RequestDTO requestDTO);

	ResultDTO updateSeatMap(SeatMapDTO seatMapDTO, RequestDTO requestDTO);

    Page<SeatMap> getAllSeatMaps(Pageable pageable);

    Page<SeatMap> getAllSeatMaps(Specification<SeatMap> spec, Pageable pageable);

	ResponseEntity<SeatMapPageDTO> getSeatMaps(SeatMapSearchDTO seatMapSearchDTO);
	
	List<SeatMapDTO> convertSeatMapsToSeatMapDTOs(List<SeatMap> seatMaps, SeatMapConvertCriteriaDTO convertCriteria);

	SeatMapDTO getSeatMapDTOById(Integer seatMapId);







}





