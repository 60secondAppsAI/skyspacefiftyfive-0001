package com.skyspacefiftyfive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspacefiftyfive.domain.MealOption;
import com.skyspacefiftyfive.dto.MealOptionDTO;
import com.skyspacefiftyfive.dto.MealOptionSearchDTO;
import com.skyspacefiftyfive.dto.MealOptionPageDTO;
import com.skyspacefiftyfive.dto.MealOptionConvertCriteriaDTO;
import com.skyspacefiftyfive.service.GenericService;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MealOptionService extends GenericService<MealOption, Integer> {

	List<MealOption> findAll();

	ResultDTO addMealOption(MealOptionDTO mealOptionDTO, RequestDTO requestDTO);

	ResultDTO updateMealOption(MealOptionDTO mealOptionDTO, RequestDTO requestDTO);

    Page<MealOption> getAllMealOptions(Pageable pageable);

    Page<MealOption> getAllMealOptions(Specification<MealOption> spec, Pageable pageable);

	ResponseEntity<MealOptionPageDTO> getMealOptions(MealOptionSearchDTO mealOptionSearchDTO);
	
	List<MealOptionDTO> convertMealOptionsToMealOptionDTOs(List<MealOption> mealOptions, MealOptionConvertCriteriaDTO convertCriteria);

	MealOptionDTO getMealOptionDTOById(Integer mealOptionId);







}





