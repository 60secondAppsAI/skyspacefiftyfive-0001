package com.skyspacefiftyfive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspacefiftyfive.domain.Flight;
import com.skyspacefiftyfive.dto.FlightDTO;
import com.skyspacefiftyfive.dto.FlightSearchDTO;
import com.skyspacefiftyfive.dto.FlightPageDTO;
import com.skyspacefiftyfive.dto.FlightConvertCriteriaDTO;
import com.skyspacefiftyfive.service.GenericService;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FlightService extends GenericService<Flight, Integer> {

	List<Flight> findAll();

	ResultDTO addFlight(FlightDTO flightDTO, RequestDTO requestDTO);

	ResultDTO updateFlight(FlightDTO flightDTO, RequestDTO requestDTO);

    Page<Flight> getAllFlights(Pageable pageable);

    Page<Flight> getAllFlights(Specification<Flight> spec, Pageable pageable);

	ResponseEntity<FlightPageDTO> getFlights(FlightSearchDTO flightSearchDTO);
	
	List<FlightDTO> convertFlightsToFlightDTOs(List<Flight> flights, FlightConvertCriteriaDTO convertCriteria);

	FlightDTO getFlightDTOById(Integer flightId);







}





