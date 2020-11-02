package com.user.booking.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SecurityServiceImplTest {
	
	
	 SecurityServiceImpl securityServiceImpl= new SecurityServiceImpl();
	
	@MockBean
	private AuthenticationManager authenticationManager;

	@MockBean
	private UserDetailsService userDetailsService;
	@MockBean
	private UserDetails userDetails;
	@MockBean
	private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
	@MockBean
	private SecurityContextHolder securityContextHolder;
	
	
	@Test
	public void testFindLoggedInUsername() {
		
		try {
		
			//int a=securityServiceImpl.updateBooking("Approve", 1L);
			//assertNotNull(a);
			//assertEquals(0,a);
			/*
			 * Object object = new Object(); object=userDetails.getUsername();
			 * object="Test";
			 */
			String s=securityServiceImpl.findLoggedInUsername();
			
			assertEquals(null,s);
			
			
		}catch(Exception e) {
			
			
		}
	}

	
	@Test
	public void testAutoLogin() {
		
		try {
		
			//int a=securityServiceImpl.updateBooking("Approve", 1L);
			//assertNotNull(a);
			//assertEquals(0,a);
//			Object object = new Object();
//			object=userDetails.getUsername();
//			object="Test";
			UserDetails userDetails = userDetailsService.loadUserByUsername("Test");
			securityServiceImpl.autoLogin("UN","PWD");
			assertNotNull(userDetails);
			
			
			
		}catch(Exception e) {
			
		}
	}

	
}
