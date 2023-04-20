package com.brooklyn.xeke.trips;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.brooklyn.xeke.entity.Driver;
import com.brooklyn.xeke.entity.Trip;
import com.brooklyn.xeke.repository.DriverRepository;
import com.brooklyn.xeke.repository.TripRepository;
import com.github.javafaker.Faker;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestTripRepository {
	@Autowired
	private TripRepository repository;
	@Autowired
	private DriverRepository driverRepository;
	
	@Test
	public void TestCreateTrip() {
		Faker faker = new Faker();
		IntStream.range(0, 20).forEach(i -> {
			Date startDate = faker.date().between(new Date(0), new Date());
			Date endDate = faker.date().future(2, TimeUnit.DAYS, startDate);
			Driver driver = driverRepository.findById(new Random().nextInt(20)+1).get();
			repository.save(new Trip(startDate, endDate, driver));
		});
	}
}
