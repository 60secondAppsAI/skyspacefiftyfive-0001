package com.skyspacefiftyfive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspacefiftyfive.domain.Ticket;
import com.skyspacefiftyfive.dto.TicketDTO;
import com.skyspacefiftyfive.dto.TicketSearchDTO;
import com.skyspacefiftyfive.dto.TicketPageDTO;
import com.skyspacefiftyfive.dto.TicketConvertCriteriaDTO;
import com.skyspacefiftyfive.service.GenericService;
import com.skyspacefiftyfive.dto.common.RequestDTO;
import com.skyspacefiftyfive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TicketService extends GenericService<Ticket, Integer> {

	List<Ticket> findAll();

	ResultDTO addTicket(TicketDTO ticketDTO, RequestDTO requestDTO);

	ResultDTO updateTicket(TicketDTO ticketDTO, RequestDTO requestDTO);

    Page<Ticket> getAllTickets(Pageable pageable);

    Page<Ticket> getAllTickets(Specification<Ticket> spec, Pageable pageable);

	ResponseEntity<TicketPageDTO> getTickets(TicketSearchDTO ticketSearchDTO);
	
	List<TicketDTO> convertTicketsToTicketDTOs(List<Ticket> tickets, TicketConvertCriteriaDTO convertCriteria);

	TicketDTO getTicketDTOById(Integer ticketId);







}





