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
import com.skyspacefiftyfive.dao.BoardingPassDAO;
import com.skyspacefiftyfive.domain.BoardingPass;
import com.skyspacefiftyfive.dto.BoardingPassDTO;
import com.skyspacefiftyfive.dto.BoardingPassSearchDTO;
import com.skyspacefiftyfive.dto.BoardingPassPageDTO;
import com.skyspacefiftyfive.dto.BoardingPassConvertCriteriaDTO;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;
import com.skyspacefiftyfive.service.BoardingPassService;
import com.skyspacefiftyfive.util.ControllerUtils;





@Service
public class BoardingPassServiceImpl extends GenericServiceImpl<BoardingPass, Integer> implements BoardingPassService {

    private final static Logger logger = LoggerFactory.getLogger(BoardingPassServiceImpl.class);

	@Autowired
	BoardingPassDAO boardingPassDao;

	


	@Override
	public GenericDAO<BoardingPass, Integer> getDAO() {
		return (GenericDAO<BoardingPass, Integer>) boardingPassDao;
	}
	
	public List<BoardingPass> findAll () {
		List<BoardingPass> boardingPasss = boardingPassDao.findAll();
		
		return boardingPasss;	
		
	}

	public ResultDTO addBoardingPass(BoardingPassDTO boardingPassDTO, RequestDTO requestDTO) {

		BoardingPass boardingPass = new BoardingPass();

		boardingPass.setBoardingPassId(boardingPassDTO.getBoardingPassId());


		boardingPass.setGateNumber(boardingPassDTO.getGateNumber());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		boardingPass = boardingPassDao.save(boardingPass);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<BoardingPass> getAllBoardingPasss(Pageable pageable) {
		return boardingPassDao.findAll(pageable);
	}

	public Page<BoardingPass> getAllBoardingPasss(Specification<BoardingPass> spec, Pageable pageable) {
		return boardingPassDao.findAll(spec, pageable);
	}

	public ResponseEntity<BoardingPassPageDTO> getBoardingPasss(BoardingPassSearchDTO boardingPassSearchDTO) {
	
			Integer boardingPassId = boardingPassSearchDTO.getBoardingPassId(); 
 			String gateNumber = boardingPassSearchDTO.getGateNumber(); 
 			String sortBy = boardingPassSearchDTO.getSortBy();
			String sortOrder = boardingPassSearchDTO.getSortOrder();
			String searchQuery = boardingPassSearchDTO.getSearchQuery();
			Integer page = boardingPassSearchDTO.getPage();
			Integer size = boardingPassSearchDTO.getSize();

	        Specification<BoardingPass> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, boardingPassId, "boardingPassId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, gateNumber, "gateNumber"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("gateNumber")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<BoardingPass> boardingPasss = this.getAllBoardingPasss(spec, pageable);
		
		//System.out.println(String.valueOf(boardingPasss.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(boardingPasss.getTotalPages()));
		
		List<BoardingPass> boardingPasssList = boardingPasss.getContent();
		
		BoardingPassConvertCriteriaDTO convertCriteria = new BoardingPassConvertCriteriaDTO();
		List<BoardingPassDTO> boardingPassDTOs = this.convertBoardingPasssToBoardingPassDTOs(boardingPasssList,convertCriteria);
		
		BoardingPassPageDTO boardingPassPageDTO = new BoardingPassPageDTO();
		boardingPassPageDTO.setBoardingPasss(boardingPassDTOs);
		boardingPassPageDTO.setTotalElements(boardingPasss.getTotalElements());
		return ResponseEntity.ok(boardingPassPageDTO);
	}

	public List<BoardingPassDTO> convertBoardingPasssToBoardingPassDTOs(List<BoardingPass> boardingPasss, BoardingPassConvertCriteriaDTO convertCriteria) {
		
		List<BoardingPassDTO> boardingPassDTOs = new ArrayList<BoardingPassDTO>();
		
		for (BoardingPass boardingPass : boardingPasss) {
			boardingPassDTOs.add(convertBoardingPassToBoardingPassDTO(boardingPass,convertCriteria));
		}
		
		return boardingPassDTOs;

	}
	
	public BoardingPassDTO convertBoardingPassToBoardingPassDTO(BoardingPass boardingPass, BoardingPassConvertCriteriaDTO convertCriteria) {
		
		BoardingPassDTO boardingPassDTO = new BoardingPassDTO();
		
		boardingPassDTO.setBoardingPassId(boardingPass.getBoardingPassId());

	
		boardingPassDTO.setGateNumber(boardingPass.getGateNumber());

	

		
		return boardingPassDTO;
	}

	public ResultDTO updateBoardingPass(BoardingPassDTO boardingPassDTO, RequestDTO requestDTO) {
		
		BoardingPass boardingPass = boardingPassDao.getById(boardingPassDTO.getBoardingPassId());

		boardingPass.setBoardingPassId(ControllerUtils.setValue(boardingPass.getBoardingPassId(), boardingPassDTO.getBoardingPassId()));

		boardingPass.setGateNumber(ControllerUtils.setValue(boardingPass.getGateNumber(), boardingPassDTO.getGateNumber()));



        boardingPass = boardingPassDao.save(boardingPass);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public BoardingPassDTO getBoardingPassDTOById(Integer boardingPassId) {
	
		BoardingPass boardingPass = boardingPassDao.getById(boardingPassId);
			
		
		BoardingPassConvertCriteriaDTO convertCriteria = new BoardingPassConvertCriteriaDTO();
		return(this.convertBoardingPassToBoardingPassDTO(boardingPass,convertCriteria));
	}







}
