package com.brooklyn.xeke.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brooklyn.xeke.entity.Trip;
import com.brooklyn.xeke.exception.ResourceNotFoundException;
import com.brooklyn.xeke.payload.TripDTO;
import com.brooklyn.xeke.repository.DriverRepository;
import com.brooklyn.xeke.repository.TripRepository;

@Service
public class TripService {
	@Autowired
	private TripRepository tripRepository;
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private ModelMapper mapper;

	public TripDTO addTrip(TripDTO tripDTO) {
		Trip trip = mapper.map(tripDTO, Trip.class);
		return mapper.map(tripRepository.save(trip),TripDTO.class);
	}
	public List<TripDTO> findAll() {
		List<Trip> trips = tripRepository.findAll();
		return trips.stream().map(trip -> mapper.map(trip, TripDTO.class)).collect(Collectors.toList());
	}
	public List<TripDTO> findAllTripsByDriverId(Integer driverId) {
		List<Trip> trips = tripRepository.findAllByDriverId(driverId);
		return trips.stream().map(trip -> mapper.map(trip, TripDTO.class)).collect(Collectors.toList());
	}
	public TripDTO getTrip(Integer id) {
		Trip trip = tripRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Trip", "id", String.valueOf(id)));
		return mapper.map(trip, TripDTO.class);
	}
	public TripDTO updateTrip(Integer id, TripDTO tripDTO) {
		Trip trip = tripRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Trip", "id", String.valueOf(id)));
		trip.setDriver(driverRepository.findById(tripDTO.getDriverId()).get());
		trip.setStartTime(tripDTO.getStartTime());
		trip.setEndTime(tripDTO.getEndTime());
		Trip saveTrip = tripRepository.save(trip);
		return mapper.map(saveTrip, TripDTO.class);
	}
	public void deleteTrip(Integer id) {
		Trip trip = tripRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Trip", "id", String.valueOf(id)));
		tripRepository.delete(trip);
	}
	
}
