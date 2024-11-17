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
import com.skyspacefiftyfive.dao.CrewMemberDAO;
import com.skyspacefiftyfive.domain.CrewMember;
import com.skyspacefiftyfive.dto.CrewMemberDTO;
import com.skyspacefiftyfive.dto.CrewMemberSearchDTO;
import com.skyspacefiftyfive.dto.CrewMemberPageDTO;
import com.skyspacefiftyfive.dto.CrewMemberConvertCriteriaDTO;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;
import com.skyspacefiftyfive.service.CrewMemberService;
import com.skyspacefiftyfive.util.ControllerUtils;





@Service
public class CrewMemberServiceImpl extends GenericServiceImpl<CrewMember, Integer> implements CrewMemberService {

    private final static Logger logger = LoggerFactory.getLogger(CrewMemberServiceImpl.class);

	@Autowired
	CrewMemberDAO crewMemberDao;

	


	@Override
	public GenericDAO<CrewMember, Integer> getDAO() {
		return (GenericDAO<CrewMember, Integer>) crewMemberDao;
	}
	
	public List<CrewMember> findAll () {
		List<CrewMember> crewMembers = crewMemberDao.findAll();
		
		return crewMembers;	
		
	}

	public ResultDTO addCrewMember(CrewMemberDTO crewMemberDTO, RequestDTO requestDTO) {

		CrewMember crewMember = new CrewMember();

		crewMember.setCrewMemberId(crewMemberDTO.getCrewMemberId());


		crewMember.setFirstName(crewMemberDTO.getFirstName());


		crewMember.setLastName(crewMemberDTO.getLastName());


		crewMember.setRole(crewMemberDTO.getRole());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		crewMember = crewMemberDao.save(crewMember);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CrewMember> getAllCrewMembers(Pageable pageable) {
		return crewMemberDao.findAll(pageable);
	}

	public Page<CrewMember> getAllCrewMembers(Specification<CrewMember> spec, Pageable pageable) {
		return crewMemberDao.findAll(spec, pageable);
	}

	public ResponseEntity<CrewMemberPageDTO> getCrewMembers(CrewMemberSearchDTO crewMemberSearchDTO) {
	
			Integer crewMemberId = crewMemberSearchDTO.getCrewMemberId(); 
 			String firstName = crewMemberSearchDTO.getFirstName(); 
 			String lastName = crewMemberSearchDTO.getLastName(); 
 			String role = crewMemberSearchDTO.getRole(); 
 			String sortBy = crewMemberSearchDTO.getSortBy();
			String sortOrder = crewMemberSearchDTO.getSortOrder();
			String searchQuery = crewMemberSearchDTO.getSearchQuery();
			Integer page = crewMemberSearchDTO.getPage();
			Integer size = crewMemberSearchDTO.getSize();

	        Specification<CrewMember> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, crewMemberId, "crewMemberId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, firstName, "firstName"); 
			
			spec = ControllerUtils.andIfNecessary(spec, lastName, "lastName"); 
			
			spec = ControllerUtils.andIfNecessary(spec, role, "role"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("firstName")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("lastName")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("role")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<CrewMember> crewMembers = this.getAllCrewMembers(spec, pageable);
		
		//System.out.println(String.valueOf(crewMembers.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(crewMembers.getTotalPages()));
		
		List<CrewMember> crewMembersList = crewMembers.getContent();
		
		CrewMemberConvertCriteriaDTO convertCriteria = new CrewMemberConvertCriteriaDTO();
		List<CrewMemberDTO> crewMemberDTOs = this.convertCrewMembersToCrewMemberDTOs(crewMembersList,convertCriteria);
		
		CrewMemberPageDTO crewMemberPageDTO = new CrewMemberPageDTO();
		crewMemberPageDTO.setCrewMembers(crewMemberDTOs);
		crewMemberPageDTO.setTotalElements(crewMembers.getTotalElements());
		return ResponseEntity.ok(crewMemberPageDTO);
	}

	public List<CrewMemberDTO> convertCrewMembersToCrewMemberDTOs(List<CrewMember> crewMembers, CrewMemberConvertCriteriaDTO convertCriteria) {
		
		List<CrewMemberDTO> crewMemberDTOs = new ArrayList<CrewMemberDTO>();
		
		for (CrewMember crewMember : crewMembers) {
			crewMemberDTOs.add(convertCrewMemberToCrewMemberDTO(crewMember,convertCriteria));
		}
		
		return crewMemberDTOs;

	}
	
	public CrewMemberDTO convertCrewMemberToCrewMemberDTO(CrewMember crewMember, CrewMemberConvertCriteriaDTO convertCriteria) {
		
		CrewMemberDTO crewMemberDTO = new CrewMemberDTO();
		
		crewMemberDTO.setCrewMemberId(crewMember.getCrewMemberId());

	
		crewMemberDTO.setFirstName(crewMember.getFirstName());

	
		crewMemberDTO.setLastName(crewMember.getLastName());

	
		crewMemberDTO.setRole(crewMember.getRole());

	

		
		return crewMemberDTO;
	}

	public ResultDTO updateCrewMember(CrewMemberDTO crewMemberDTO, RequestDTO requestDTO) {
		
		CrewMember crewMember = crewMemberDao.getById(crewMemberDTO.getCrewMemberId());

		crewMember.setCrewMemberId(ControllerUtils.setValue(crewMember.getCrewMemberId(), crewMemberDTO.getCrewMemberId()));

		crewMember.setFirstName(ControllerUtils.setValue(crewMember.getFirstName(), crewMemberDTO.getFirstName()));

		crewMember.setLastName(ControllerUtils.setValue(crewMember.getLastName(), crewMemberDTO.getLastName()));

		crewMember.setRole(ControllerUtils.setValue(crewMember.getRole(), crewMemberDTO.getRole()));



        crewMember = crewMemberDao.save(crewMember);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CrewMemberDTO getCrewMemberDTOById(Integer crewMemberId) {
	
		CrewMember crewMember = crewMemberDao.getById(crewMemberId);
			
		
		CrewMemberConvertCriteriaDTO convertCriteria = new CrewMemberConvertCriteriaDTO();
		return(this.convertCrewMemberToCrewMemberDTO(crewMember,convertCriteria));
	}







}
