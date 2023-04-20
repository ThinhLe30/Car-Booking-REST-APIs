package com.brooklyn.xeke.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brooklyn.xeke.entity.Driver;
import com.brooklyn.xeke.exception.ResourceNotFoundException;
import com.brooklyn.xeke.payload.DriverDTO;
import com.brooklyn.xeke.repository.DriverRepository;

@Service
public class DriverService {
	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private ModelMapper mapper;

	
	public DriverDTO addDriver(DriverDTO driverDTO) {
		Driver driver = mapper.map(driverDTO, Driver.class);
		return mapper.map(driverRepository.save(driver),DriverDTO.class);
	}
	
	public List<DriverDTO> findAll() {
		List<Driver> drivers = driverRepository.findAll();
		return drivers.stream().map(driver -> mapper.map(driver, DriverDTO.class)).collect(Collectors.toList());
	}
	public DriverDTO getDriver(Integer id) {
		Driver driver = driverRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Driver", "id", String.valueOf(id)));
		return mapper.map(driver, DriverDTO.class);
	}
	public DriverDTO updateDriver(Integer id, DriverDTO driverDTO) {
		Driver driver = driverRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Driver", "id", String.valueOf(id)));
		driver.setAddress(driverDTO.getAddress());
		driver.setEmail(driverDTO.getEmail());
		driver.setFirstName(driverDTO.getFirstName());
		driver.setLastName(driverDTO.getLastName());
		driver.setPhone(driverDTO.getPhone());
		
		Driver savedDriver = driverRepository.save(driver);	
		return mapper.map(savedDriver, DriverDTO.class);
	}
	public void deleteDriver(Integer id) {
		// TODO Auto-generated method stub
		Driver driver = driverRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Driver", "id", String.valueOf(id)));
		driverRepository.delete(driver);
	}
	
}
