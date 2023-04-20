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
import org.springframework.web.bind.annotation.RestController;

import com.brooklyn.xeke.payload.DriverDTO;
import com.brooklyn.xeke.payload.TripDTO;
import com.brooklyn.xeke.repository.TripRepository;
import com.brooklyn.xeke.service.DriverService;
import com.brooklyn.xeke.service.TripService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class TripController {
	@Autowired
	private TripService tripService;	
	@GetMapping("/trips")
	public List<TripDTO> findAll(){
		return tripService.findAll();
	}
	@GetMapping("/drivers/{id}/trips")
	public List<TripDTO> findAllTripByDriver(@PathVariable(name = "id") Integer id) {
		return tripService.findAllTripsByDriverId(id);
	}
	@PostMapping("/trips")
	public ResponseEntity<TripDTO> addTrip(@Valid @RequestBody TripDTO tripDTO){
		return new ResponseEntity<>(tripService.addTrip(tripDTO), HttpStatus.CREATED);
	}
	@GetMapping("/trips/{id}")
	public ResponseEntity<TripDTO> getTrip(@PathVariable(name = "id") Integer id) {
		return new ResponseEntity<TripDTO>(tripService.getTrip(id), HttpStatus.OK);
	}
	@PutMapping("/trips/{id}")
	public ResponseEntity<TripDTO> updateTrip(@PathVariable(name = "id") Integer id, @Valid @RequestBody TripDTO tripDTO){
		return new ResponseEntity<TripDTO>(tripService.updateTrip(id, tripDTO), HttpStatus.OK); 
	}
	@DeleteMapping("/trips/{id}")
	public ResponseEntity<String> deleteDriver(@PathVariable(name = "id") Integer id){
		tripService.deleteTrip(id);
		return new ResponseEntity<String>("Trip was deleted successfully!", HttpStatus.OK);
	}
}
