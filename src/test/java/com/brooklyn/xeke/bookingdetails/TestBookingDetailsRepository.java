package com.brooklyn.xeke.bookingdetails;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.brooklyn.xeke.entity.BookingDetail;
import com.brooklyn.xeke.entity.Driver;
import com.brooklyn.xeke.entity.Sheet;
import com.brooklyn.xeke.repository.BookingDetailRepository;
import com.brooklyn.xeke.repository.TripRepository;
import com.github.javafaker.Faker;
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestBookingDetailsRepository {
	@Autowired
	private BookingDetailRepository bookingDetailRepository;
	@Autowired
	private TripRepository tripRepository;
	@Test
	public void testCreateBookingDetails() {
		Faker faker = new Faker();
		IntStream.range(0, 40).forEach(i -> {
			BookingDetail detail = new BookingDetail();
			detail.setFirstName(faker.name().firstName());
			detail.setLastName(faker.name().lastName());
			detail.setPickDestination(faker.address().city());
			detail.setDropDestination(faker.address().city());
			detail.setPhone(faker.phoneNumber().cellPhone());
			detail.setSheet(faker.options().option(Sheet.class));
			detail.setStatus(faker.bool().bool());
			detail.setTrip(tripRepository.findById(new Random().nextInt(30)+1).get());
			bookingDetailRepository.save(detail);
		});
	}
	@Test
	public void setDate() {
		List<BookingDetail> findAll = bookingDetailRepository.findAll();
		for(BookingDetail booking : findAll) {
			booking.setDateBooking(new Date());
			bookingDetailRepository.save(booking);
		}
	}
}
