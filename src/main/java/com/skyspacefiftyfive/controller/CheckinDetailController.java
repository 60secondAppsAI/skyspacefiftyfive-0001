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

import com.skyspacefiftyfive.domain.CheckinDetail;
import com.skyspacefiftyfive.dto.CheckinDetailDTO;
import com.skyspacefiftyfive.dto.CheckinDetailSearchDTO;
import com.skyspacefiftyfive.dto.CheckinDetailPageDTO;
import com.skyspacefiftyfive.service.CheckinDetailService;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/checkinDetail")
@RestController
public class CheckinDetailController {

	private final static Logger logger = LoggerFactory.getLogger(CheckinDetailController.class);

	@Autowired
	CheckinDetailService checkinDetailService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CheckinDetail> getAll() {

		List<CheckinDetail> checkinDetails = checkinDetailService.findAll();
		
		return checkinDetails;	
	}

	@GetMapping(value = "/{checkinDetailId}")
	@ResponseBody
	public CheckinDetailDTO getCheckinDetail(@PathVariable Integer checkinDetailId) {
		
		return (checkinDetailService.getCheckinDetailDTOById(checkinDetailId));
	}

 	@RequestMapping(value = "/addCheckinDetail", method = RequestMethod.POST)
	public ResponseEntity<?> addCheckinDetail(@RequestBody CheckinDetailDTO checkinDetailDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = checkinDetailService.addCheckinDetail(checkinDetailDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/checkinDetails")
	public ResponseEntity<CheckinDetailPageDTO> getCheckinDetails(CheckinDetailSearchDTO checkinDetailSearchDTO) {
 
		return checkinDetailService.getCheckinDetails(checkinDetailSearchDTO);
	}	

	@RequestMapping(value = "/updateCheckinDetail", method = RequestMethod.POST)
	public ResponseEntity<?> updateCheckinDetail(@RequestBody CheckinDetailDTO checkinDetailDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = checkinDetailService.updateCheckinDetail(checkinDetailDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
