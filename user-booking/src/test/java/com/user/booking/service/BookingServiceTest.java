package com.user.booking.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.user.booking.repository.BookingRepository;

@RunWith(SpringRunner.class)
public class BookingServiceTest {
	
	@MockBean
	BookingRepository bookingRepository;
	
	@Test
	public void testupdateBooking() {
		
		try {
			BookingService bookingService= new BookingService();
			int a=bookingService.updateBooking("Approve", 1L);
						assertEquals(0,a);
			
		}catch(Exception e) {
			
			
		}
	}
		
		@Test
		public void testDeleteBooking() {
			
			try {
				BookingService bookingService= new BookingService();
				bookingService.deleteBooking(1L);
				assertNotNull(bookingService);
				
				
			}catch(Exception e) {
				
				
			}
		
	}

}
