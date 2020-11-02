package com.user.booking.repository;



import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.user.booking.model.Booking;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class BookingRepositoryTest {
	
	@Autowired
    BookingRepository bookingRepository; 


	
		@Test
		public void testFindbyStatus() {
			try {				
				List<Booking> bookingList1=bookingRepository.findbyStatus("APPROVED");
				assertNotNull(bookingList1);
			   assertEquals(2,bookingRepository.findbyStatus("APPROVED").size());
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		
	}
	
		
		@Test
		public void testFindById() {
			try {			
			Booking booking = new Booking();
			booking=bookingRepository.findbyId(2L);
			assertNotNull(booking);
			
			  assertEquals(new Long(2L),bookingRepository.findbyId(2L).getId());
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	
		

		@Test
		public void testUpdateBooking() {
			try {
					
						
			  assertEquals(0,bookingRepository.updateBooking(Mockito.anyString(),Mockito.anyLong()));
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		@Test
		public void testDeleteBooking() {
			try {	
			
				Booking booking= new Booking();
			 bookingRepository.deleteBooking(new Long(4L));
			 assertNotNull(booking);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		

		
		
}
