package com.skyspacefiftyfive.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.skyspacefiftyfive.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.skyspacefiftyfive.domain.SeatMap;
import com.skyspacefiftyfive.dto.SeatMapDTO;
import com.skyspacefiftyfive.dto.SeatMapSearchDTO;
import com.skyspacefiftyfive.dto.SeatMapPageDTO;
import com.skyspacefiftyfive.service.SeatMapService;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/seatMap")
@RestController
public class SeatMapController {

	private final static Logger logger = LoggerFactory.getLogger(SeatMapController.class);

	@Autowired
	SeatMapService seatMapService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<SeatMap> getAll() {

		List<SeatMap> seatMaps = seatMapService.findAll();
		
		return seatMaps;	
	}

	@GetMapping(value = "/{seatMapId}")
	@ResponseBody
	public SeatMapDTO getSeatMap(@PathVariable Integer seatMapId) {
		
		return (seatMapService.getSeatMapDTOById(seatMapId));
	}

 	@RequestMapping(value = "/addSeatMap", method = RequestMethod.POST)
	public ResponseEntity<?> addSeatMap(@RequestBody SeatMapDTO seatMapDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = seatMapService.addSeatMap(seatMapDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/seatMaps")
	public ResponseEntity<SeatMapPageDTO> getSeatMaps(SeatMapSearchDTO seatMapSearchDTO) {
 
		return seatMapService.getSeatMaps(seatMapSearchDTO);
	}	

	@RequestMapping(value = "/updateSeatMap", method = RequestMethod.POST)
	public ResponseEntity<?> updateSeatMap(@RequestBody SeatMapDTO seatMapDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = seatMapService.updateSeatMap(seatMapDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
