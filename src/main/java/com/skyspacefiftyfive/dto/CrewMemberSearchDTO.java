package com.skyspacefiftyfive.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CrewMemberSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer crewMemberId;
	
	private String firstName;
	
	private String lastName;
	
	private String role;
	
}
