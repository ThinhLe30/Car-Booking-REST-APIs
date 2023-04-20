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
import com.brooklyn.xeke.service.DriverService;
import com.brooklyn.xeke.service.TripService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class DriverController {
	@Autowired
	private DriverService driverService;
	@Autowired
	private TripService tripService;
	@GetMapping("/drivers")
	public List<DriverDTO> findAll(){
		return driverService.findAll();
	}
	@PostMapping("/drivers")
	public ResponseEntity<DriverDTO> addDriver(@Valid @RequestBody DriverDTO driverDTO){
		return new ResponseEntity<>(driverService.addDriver(driverDTO), HttpStatus.CREATED);
	}
	@GetMapping("/drivers/{id}")
	public ResponseEntity<DriverDTO> getDriver(@PathVariable(name = "id") Integer id) {
		return new ResponseEntity<DriverDTO>(driverService.getDriver(id), HttpStatus.OK);
	}
	@PutMapping("/drivers/{id}")
	public ResponseEntity<DriverDTO> updateDriver(@PathVariable(name = "id") Integer id, @Valid @RequestBody DriverDTO driverDTO){
		return new ResponseEntity<DriverDTO>(driverService.updateDriver(id, driverDTO), HttpStatus.OK); 
	}
	@DeleteMapping("/drivers/{id}")
	public ResponseEntity<String> deleteDriver(@PathVariable(name = "id") Integer id){
		driverService.deleteDriver(id);
		return new ResponseEntity<String>("Driver was deleted successfully!", HttpStatus.OK);
	}
	
	
}
