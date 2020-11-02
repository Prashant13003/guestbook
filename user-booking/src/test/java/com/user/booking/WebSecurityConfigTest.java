package com.user.booking;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import com.user.booking.model.Role;
import com.user.booking.model.User;
import com.user.booking.repository.UserRepository;
import com.user.booking.service.UserDetailsServiceImpl;

@RunWith(SpringRunner.class)
public class WebSecurityConfigTest {
		
	
	@MockBean
	 UserDetailsService userDetailsService ;
	@MockBean
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	 WebSecurityConfig webSecurityConfig = new WebSecurityConfig();
	//@Mock
	//UserRepository  userRepository; 
	
	@MockBean
	private UserRepository  userRepository;
	
	@Autowired
    ApplicationContext context;
	
	 
	
	UserDetails userDetail;

	@BeforeClass
	public static void setup() {
		// Create application context instance
		//webSecurityConfig = new WebSecurityConfig();
		// Get user details service configured in configuration
		
		//userRepository=createUserRepository();
	}
	
	
	
		
	
	/**
	 * Test the valid user with valid role
	 * 
	 * @throws Exception
	 */
	
	
	@Test
	public void testValidRole() throws Exception {
		try {			
			 User user = new User();
			 Set<Role> roles= new HashSet<Role>();
			 Role role= new Role();
			 role.setName("ROLE_ADMIN");
			 roles.add(role);
			 user.setUsername("Prashant");
			 user.setPassword("123456");
			 user.setPasswordConfirm("123456");
			 user.setRoles(roles);
			 UserVerifcationService service1= new UserVerifcationService();
			Collection<? extends GrantedAuthority> authorities =  service1.method();
			 service1.setId(1L);
			 service1.setUsername("Prashant");
			 service1.setPassword("123456");
			 service1.setAuthorities(authorities);
			
			
	        UserDetails userDetails = userDetailsService.loadUserByUsername ("Prashant");
	        Mockito.when(userDetailsService.loadUserByUsername (Mockito.anyString())).thenReturn(service1);
	        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(user);
	        
	        System.out.println("userDetails"+userDetails);
	        Authentication authToken = new UsernamePasswordAuthenticationToken (service1.getUsername(), service1.getPassword(), service1.getAuthorities());
	        SecurityContextHolder.getContext().setAuthentication(authToken);
	        UserVerifcationService service = (UserVerifcationService) webSecurityConfig.authenticationManagerBean(); 
	       service.method();
	       assertNotNull(service);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	/*  *//**
			 * Test the valid user with INVALID role
			 * 
			 * @throws Exception
			 */
	
	//@Test (expected = AccessDeniedException.class)
	public void testInvalidRole()throws Exception {
		try {
			UserDetails userDetails =userDetailsService.loadUserByUsername ("lokesh");
			// when(userRepository.findByUsername("lokesh")).thenReturn(user);
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(authorities.get(0)); Authentication authToken = new
					UsernamePasswordAuthenticationToken (userDetails.getUsername(),
							userDetails.getPassword(), authorities);
			SecurityContextHolder.getContext().setAuthentication(authToken);
			UserVerifcationService service = (UserVerifcationService)
					webSecurityConfig.authenticationManagerBean(); 
			service.method();
		}catch(Exception e) {
			//e.printStackTrace();
		}}
	  
	
			 

}
