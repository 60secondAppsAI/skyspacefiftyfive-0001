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

import com.skyspacefiftyfive.domain.TravelClass;
import com.skyspacefiftyfive.dto.TravelClassDTO;
import com.skyspacefiftyfive.dto.TravelClassSearchDTO;
import com.skyspacefiftyfive.dto.TravelClassPageDTO;
import com.skyspacefiftyfive.service.TravelClassService;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/travelClass")
@RestController
public class TravelClassController {

	private final static Logger logger = LoggerFactory.getLogger(TravelClassController.class);

	@Autowired
	TravelClassService travelClassService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<TravelClass> getAll() {

		List<TravelClass> travelClasss = travelClassService.findAll();
		
		return travelClasss;	
	}

	@GetMapping(value = "/{travelClassId}")
	@ResponseBody
	public TravelClassDTO getTravelClass(@PathVariable Integer travelClassId) {
		
		return (travelClassService.getTravelClassDTOById(travelClassId));
	}

 	@RequestMapping(value = "/addTravelClass", method = RequestMethod.POST)
	public ResponseEntity<?> addTravelClass(@RequestBody TravelClassDTO travelClassDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = travelClassService.addTravelClass(travelClassDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/travelClasss")
	public ResponseEntity<TravelClassPageDTO> getTravelClasss(TravelClassSearchDTO travelClassSearchDTO) {
 
		return travelClassService.getTravelClasss(travelClassSearchDTO);
	}	

	@RequestMapping(value = "/updateTravelClass", method = RequestMethod.POST)
	public ResponseEntity<?> updateTravelClass(@RequestBody TravelClassDTO travelClassDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = travelClassService.updateTravelClass(travelClassDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
