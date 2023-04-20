package com.brooklyn.xeke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brooklyn.xeke.entity.BookingDetail;

public interface BookingDetailRepository extends JpaRepository<BookingDetail, Integer>{
	 List<BookingDetail> findAllByOrderByDateBookingDesc();
}
