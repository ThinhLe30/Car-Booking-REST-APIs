package com.brooklyn.xeke.service;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.brooklyn.xeke.entity.BookingDetail;
import com.brooklyn.xeke.exception.ResourceNotFoundException;
import com.brooklyn.xeke.payload.BookingDetailDTO;
import com.brooklyn.xeke.payload.BookingDetailResponse;
import com.brooklyn.xeke.repository.BookingDetailRepository;
import com.brooklyn.xeke.repository.TripRepository;

@Service
public class BookingDetailService {
	@Autowired 
	private BookingDetailRepository bookingDetailRepository;
	@Autowired
	private TripRepository tripRepository;
	@Autowired
	private ModelMapper mapper;
	
	public BookingDetailDTO addBookingDetail(BookingDetailDTO detailDTO) {
		BookingDetail detail = mapper.map(detailDTO, BookingDetail.class);
		return mapper.map(bookingDetailRepository.save(detail),BookingDetailDTO.class);
	}
	
	public BookingDetailResponse findAll(int pageNo, int pageSize, String sortField, String orderBy) {
		Sort sort = orderBy.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<BookingDetail> details = bookingDetailRepository.findAll(pageable);
		List<BookingDetail> listOfDetails = details.getContent();
		
		List<BookingDetailDTO> content = listOfDetails.stream().map(detail -> mapper.map(detail,BookingDetailDTO.class )).collect(Collectors.toList());
		BookingDetailResponse response = new BookingDetailResponse();
		response.setContent(content);
		response.setPageNo(pageNo);
		response.setPageSize(pageSize);
		response.setTotalElements(details.getTotalElements());
		response.setTotalPages(details.getTotalPages());
		response.setLast(details.isLast());
		response.setSortField(sortField);
		response.setOrderBy(orderBy);
		String reverseOrderBy = orderBy.equalsIgnoreCase(Sort.Direction.ASC.name()) ? "desc" : "asc";
		response.setReverseOrderBy(reverseOrderBy);

		return response;
	}
	public BookingDetailDTO getBookingDetail(Integer id) {
		BookingDetail detail = bookingDetailRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Booking Detail", "id", String.valueOf(id)));
		return mapper.map(detail, BookingDetailDTO.class);
	}
	public BookingDetailDTO updateBookingDetail(Integer id, BookingDetailDTO detailDTO) {
		BookingDetail detail = bookingDetailRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Booking Detail", "id", String.valueOf(id)));
		detail.setFirstName(detailDTO.getFirstName());
		detail.setLastName(detailDTO.getLastName());
		detail.setDropDestination(detailDTO.getDropDestination());
		detail.setPhone(detailDTO.getPhone());
		detail.setPickDestination(detailDTO.getPickDestination());
		detail.setSheet(detailDTO.getSheet());
		detail.setStatus(detailDTO.isStatus());
		detail.setTrip(tripRepository.findById(detailDTO.getTripId()).get());
		detail.setDateBooking(detailDTO.getDateBooking());
		BookingDetail save = bookingDetailRepository.save(detail);	
		return mapper.map(save, BookingDetailDTO.class);
	}
	public void deleteBookingDetail(Integer id) {
		BookingDetail detail = bookingDetailRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Booking Detail", "id", String.valueOf(id)));
		bookingDetailRepository.delete(detail);
	}
}
