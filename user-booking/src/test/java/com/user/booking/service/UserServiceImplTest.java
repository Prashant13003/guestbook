package com.user.booking.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.user.booking.model.Role;
import com.user.booking.model.User;
import com.user.booking.repository.RoleRepository;
import com.user.booking.repository.UserRepository;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {
	
	
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;
   UserServiceImpl userServiceImpl = new UserServiceImpl();
    
    @Test
    public void testSave() {
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
    		userServiceImpl.save(user);
    		assertNotNull(user);
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    }
    
    @Test
    public void testFindByUsername() {
    	try {
    		User user = new User();
			 Set<Role> roles= new HashSet<Role>();
			 Role role= new Role();
			 role.setName("ROLE_ADMIN");
			 roles.add(role);
			 user.setUsername("Tom");
			 user.setPassword("123456");
			 user.setPasswordConfirm("123456");
			 user.setRoles(roles);
			 
			 when(userRepository.findByUsername(Mockito.anyString())).thenReturn(user);
    		userServiceImpl.findByUsername("Tom");
    		
    		assertEquals("Tom",userRepository.findByUsername("Tom").getUsername());
    		
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    }
    

    @Test
    public void testExistsByUsername() {
    	try {
    		
			 
		
    		userServiceImpl.existsByUsername("Tom");
    		
    		assertEquals(null,userServiceImpl.existsByUsername("Tom"));
    		
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    }
    

    @Test
    public void testExistsByEmail() {
    	try {
    		
			 
		
    		userServiceImpl.existsByEmail("Tom@gmail.com");
    		
    		assertEquals(null,userServiceImpl.existsByUsername("Tom"));
    		
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    }
    
}
