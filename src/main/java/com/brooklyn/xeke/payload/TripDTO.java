package com.brooklyn.xeke.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class TripDTO {
	private Integer id;
	private Date startTime;
	private Date endTime;
	private Integer driverId;
	private Set<BookingDetailDTO> bookingDetails = new HashSet<>();
	
	
	
	public Set<BookingDetailDTO> getBookingDetails() {
		return bookingDetails;
	}


	public void setBookingDetails(Set<BookingDetailDTO> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}


	public Integer getId() {
		return id;
	}
	

	public void setId(Integer id) {
		this.id = id;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}


	public TripDTO() {
		super();
	}
	
	
	
}
