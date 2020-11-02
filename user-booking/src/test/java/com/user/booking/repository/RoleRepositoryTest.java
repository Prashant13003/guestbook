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

import com.user.booking.model.Role;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class RoleRepositoryTest {
	
	@Autowired
	private RoleRepository roleRepository;
	
	 @Test
	 public void testFindByUsername() {		
		try {
			Role role1= new Role();
			
			role1=roleRepository.findByName("ROLE_USER");
				assertNotNull(role1);
				assertEquals(new Long(1L),role1.getId());
			assertEquals("ROLE_USER",roleRepository.findByName("ROLE_USER").getName());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
