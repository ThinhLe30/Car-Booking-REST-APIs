package com.brooklyn.xeke.drivers;

import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.brooklyn.xeke.entity.Driver;
import com.brooklyn.xeke.repository.DriverRepository;
import com.github.javafaker.Faker;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestDriverRepository {
	@Autowired
	private DriverRepository driverRepository;
	
	
	@Test
	public void tetsCreateDriver() {
		Faker faker = new Faker();
		IntStream.range(0, 20).forEach(i -> {
			driverRepository.save(new Driver(
						faker.name().firstName(),
						faker.name().lastName(),
						faker.phoneNumber().cellPhone(),
						faker.internet().emailAddress(),
						faker.address().fullAddress()
				));
		});
	}
}
