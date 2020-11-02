package com.user.booking.validator;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.Errors;

import com.user.booking.model.User;
import com.user.booking.repository.UserRepository;
import com.user.booking.service.UserService;


@RunWith(SpringRunner.class)
public class UserValidatorTest {
	
	
	@MockBean
	private UserRepository userRepository;
	@MockBean
	private Errors errors;
	@MockBean
	private UserService userService1 ;
	
	@Test
	public void testValidate() {
		try {
			
			UserValidator userValidator= new UserValidator();
			
			User user= new User();
			user.setId(1L);
			user.setPassword("12233");
			user.setUsername("Tom");
			user.setPasswordConfirm("12233");
			
			//UserService userService =new  UserServiceImpl();
			when(userService1.findByUsername("Tom")).thenReturn(user);
			//UserRepository userRepository= new UserRepositortTest();
			//Mockito.spy(UserRepository.class);
			when(userRepository.findByUsername("Tom")).thenReturn(user);
			
			//Errors errors=(Errors) RETURNS_SELF;  
			//errors.rejectValue("name", "Size.booking.name");
			userValidator.validate(user,  errors);
			
		}catch(Exception e) {
			
		}
		
	}
}
