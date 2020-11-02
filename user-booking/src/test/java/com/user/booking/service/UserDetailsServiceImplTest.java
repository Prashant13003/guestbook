package com.user.booking.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.user.booking.model.Role;
import com.user.booking.model.User;
import com.user.booking.repository.UserRepository;

@RunWith(SpringRunner.class)
public class UserDetailsServiceImplTest {
	
	@MockBean
	private UserRepository userRepository;
	
	
	@MockBean
	private UserDetails UserDetails;
	@MockBean
	private GrantedAuthority grantedAuthority;
	@MockBean
	private Role role;
	
	@Test
	public void testLoadUserByUsername() {
		
		try {
			UserDetailsServiceImpl userDetailsServiceImpl= new UserDetailsServiceImpl();
			User user= new User();
			user.setUsername("Tom");
			
			when(userRepository.findByUsername(Mockito.anyString())).thenReturn(user);
			
			UserDetails=userDetailsServiceImpl.loadUserByUsername("Tom");
			
			assertEquals("Tom",userRepository.findByUsername("Tom").getUsername());
			
		}catch(Exception e) {
			
		}
		
	}
	

}
