package com.user.booking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.booking.repository.BookingRepository;

/**
 * 
 * Service class for Booking Service.
 *
 */
@Service("BookingService")
@Scope("prototype")
@Qualifier("bookingService")
@Transactional
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;

	private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int updateBooking(String status, Long id) {
		logger.info("Status " + status);
		return bookingRepository.updateBooking(status, id);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteBooking(Long id) {
		logger.info("Booking ID " + id);
		bookingRepository.deleteBooking(id);
	}

}
