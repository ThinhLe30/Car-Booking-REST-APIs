package com.brooklyn.xeke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.brooklyn.xeke.entity.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer>{

	 List<Trip> findAllByDriverId(Integer driverId);
	
}
