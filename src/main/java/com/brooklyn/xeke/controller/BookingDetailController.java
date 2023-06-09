package com.brooklyn.xeke.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brooklyn.xeke.payload.AppConstrant;
import com.brooklyn.xeke.payload.BookingDetailDTO;
import com.brooklyn.xeke.payload.BookingDetailResponse;
import com.brooklyn.xeke.service.BookingDetailService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class BookingDetailController {
	@Autowired
	private BookingDetailService detailService;
	
	@GetMapping("/booking-details")
	public BookingDetailResponse findAll(
			@RequestParam(value = "pageNo", defaultValue = AppConstrant.BOOKING_DETAIL_DEFAULT_PAGE_NUMBER,required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstrant.BOOKING_DETAIL_DEFAULT_PAGE_SIZE,required = false) int pageSize,
			@RequestParam(value = "sortField", defaultValue = AppConstrant.BOOKING_DETAIL_DEFAULT_SORT_FIELD,required = false) String sortField,
			@RequestParam(value = "orderBy", defaultValue = AppConstrant.BOOKING_DETAIL_DEFAULT_ORDER_BY,required = false) String orderBy	
			){
		return detailService.findAll(pageNo, pageSize, sortField, orderBy);
	}
	
	@GetMapping("/booking-details/{id}")
	public ResponseEntity<BookingDetailDTO> getBookingDetail(@PathVariable(name = "id") Integer id) {
		return new ResponseEntity<BookingDetailDTO>(detailService.getBookingDetail(id), HttpStatus.OK);
	}
	
	@PostMapping("/booking-details")
	public ResponseEntity<BookingDetailDTO> addBookingDetail(@Valid @RequestBody BookingDetailDTO detailDTO){
		return new ResponseEntity<>(detailService.addBookingDetail(detailDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/booking-details/{id}")
	public ResponseEntity<BookingDetailDTO> updateBookingDetail(@PathVariable(name = "id") Integer id, @Valid @RequestBody BookingDetailDTO detailDTO){
		System.out.println(id);
		return new ResponseEntity<BookingDetailDTO>(detailService.updateBookingDetail(id, detailDTO), HttpStatus.OK); 
	}
	
	@DeleteMapping("/booking-details/{id}")
	public ResponseEntity<String> deleteBookingDetail(@PathVariable(name = "id") Integer id){
		detailService.deleteBookingDetail(id);
		return new ResponseEntity<String>("Booking Detail was deleted successfully!", HttpStatus.OK);
	}
	
}
