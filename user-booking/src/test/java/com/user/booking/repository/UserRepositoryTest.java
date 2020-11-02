package com.user.booking.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.user.booking.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	

	
	 @Test
	 public void testFindByUsername() {		
		 try {
				User user= new User();
				
				user=userRepository.findByUsername("pk00608146");
					assertNotNull(user);
					assertEquals(new Long(3L),user.getId());
				assertEquals("pk00608146",userRepository.findByUsername("pk00608146").getUsername());
				
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
}
