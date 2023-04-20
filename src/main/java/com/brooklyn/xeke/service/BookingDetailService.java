package com.brooklyn.xeke.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brooklyn.xeke.entity.BookingDetail;
import com.brooklyn.xeke.exception.ResourceNotFoundException;
import com.brooklyn.xeke.payload.BookingDetailDTO;
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
	
	public List<BookingDetailDTO> findAll() {
		List<BookingDetail> details = bookingDetailRepository.findAllByOrderByDateBookingDesc();
		return details.stream().map(detail -> mapper.map(detail, BookingDetailDTO.class)).collect(Collectors.toList());
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
