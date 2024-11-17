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

import com.skyspacefiftyfive.domain.MealOption;
import com.skyspacefiftyfive.dto.MealOptionDTO;
import com.skyspacefiftyfive.dto.MealOptionSearchDTO;
import com.skyspacefiftyfive.dto.MealOptionPageDTO;
import com.skyspacefiftyfive.service.MealOptionService;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/mealOption")
@RestController
public class MealOptionController {

	private final static Logger logger = LoggerFactory.getLogger(MealOptionController.class);

	@Autowired
	MealOptionService mealOptionService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<MealOption> getAll() {

		List<MealOption> mealOptions = mealOptionService.findAll();
		
		return mealOptions;	
	}

	@GetMapping(value = "/{mealOptionId}")
	@ResponseBody
	public MealOptionDTO getMealOption(@PathVariable Integer mealOptionId) {
		
		return (mealOptionService.getMealOptionDTOById(mealOptionId));
	}

 	@RequestMapping(value = "/addMealOption", method = RequestMethod.POST)
	public ResponseEntity<?> addMealOption(@RequestBody MealOptionDTO mealOptionDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = mealOptionService.addMealOption(mealOptionDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/mealOptions")
	public ResponseEntity<MealOptionPageDTO> getMealOptions(MealOptionSearchDTO mealOptionSearchDTO) {
 
		return mealOptionService.getMealOptions(mealOptionSearchDTO);
	}	

	@RequestMapping(value = "/updateMealOption", method = RequestMethod.POST)
	public ResponseEntity<?> updateMealOption(@RequestBody MealOptionDTO mealOptionDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = mealOptionService.updateMealOption(mealOptionDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
