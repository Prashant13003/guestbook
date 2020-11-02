package com.user.booking.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import com.user.booking.CustomFileValidator;
import com.user.booking.model.Booking;
import com.user.booking.model.BookingForm;
import com.user.booking.model.Role;
import com.user.booking.model.User;
import com.user.booking.repository.BookingRepository;
import com.user.booking.service.BookingService;
import com.user.booking.service.UserService;
import com.user.booking.validator.UserValidator;

@RunWith(SpringRunner.class)
public class UserControllerTest {

	
	@MockBean
	BindingResult bindingResult;
	
	@MockBean
	UserValidator userValidator;

	@MockBean
	private UserService userService;
	@MockBean
	private HttpServletRequest request;
	@MockBean
	private HttpServletResponse response;
	
	@MockBean
	private BookingRepository bookingRepository;
	@MockBean
	private Authentication authentication;
	
	@MockBean
    private CustomFileValidator customFileValidator;
	@MockBean
	private SecurityContextHolder securityContextHolder;
	@MockBean
	private SecurityContext context;
	

	
	@Test
	public void testRegistration() {
		UserController uc = new UserController();
		Model model = new ConcurrentModel();
		String str = uc.registration(model);
		assertEquals("registration", str);
	}
	
	@Test
	public void testLogin() {
		UserController uc = new UserController();
		Model model = new ConcurrentModel();
		String str = uc.login(model ,"login","logout");
		assertEquals("login", str);
	}
	
	@Test
	public void testWelcome() {
		UserController uc = new UserController();
		Model model = new ConcurrentModel();
		String str = uc.welcome(model ,request,response);
		assertEquals("welcome", str);
	}
	
	@Test
	public void testHome() {
		UserController uc = new UserController();
		Model model = new ConcurrentModel();
		String str = uc.home(model ,request,response);
		assertEquals("welcome", str);
	}
	
	@Test
	public void testGetBooking() {
		UserController uc = new UserController();
		 BookingForm booking= new BookingForm();
		Model model = new ConcurrentModel();
		String str = uc.getbooking(model ,request,response,booking,bindingResult);
		assertEquals("booking", str);
	}
	
	@Test
	public void testViewBooking() {
		try {
		UserController uc = new UserController();
		
		Model model = new ConcurrentModel();
		List<Booking> bookinglistService=new ArrayList<>();
		Booking booking= new Booking();
		 booking.setId(1L);
		 booking.setName("Tom");
		 booking.setEmail("tom@gmail.com");
		 booking.setComments("Booking Guest");
		 booking.setStatus("SUBMITED");
		 bookinglistService.add(booking);
		 
		 
		 
		 when(bookingRepository.findbyStatus("SUBMITED")).thenReturn(bookinglistService);
		 
		 List<BookingForm> bookingFormLst=new ArrayList<>();
		 BookingForm bookings= new BookingForm();
		 booking.setId(1L);
		 booking.setName("Tom");
		 booking.setEmail("tom@gmail.com");
		 booking.setComments("Booking Guest");
		 bookingFormLst.add(bookings);
		
		String str = uc.viewBooking(model ,request,response);
		assertEquals("viewBooking", str);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUserRole() {
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
			UserController uc = new UserController();
			
			 when(userService.findByUsername(Mockito.anyString())).thenReturn(user);
			
			String s= uc.getUserRole(request,response);
			assertEquals(s, null);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testBooking() {
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
			UserController uc = new UserController();
			Model model = new ConcurrentModel();
			BookingForm booking= new BookingForm();
			 when(userService.findByUsername(Mockito.anyString())).thenReturn(user);
			
			String s= uc.booking(model,request,response,booking,bindingResult);
			
			assertEquals("booking",s);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testDownloadDocument() {
		try {
			
			UserController uc = new UserController();
			uc.downloadDocument(1L, response);
			assertNotNull(uc);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	@Test
	public void registrationTest2() {
		try {
		
		BindingResult bindingResult = new BeanPropertyBindingResult( "command", "command");
		UserController uc = new UserController();
		//Model model = new ConcurrentModel();
		
		

		
		
		UserValidator userValidator= new UserValidator();
		User user= new User();
		user.setUsername("TOM");
		user.setId(1L);
		user.setPassword("12345");
		user.setPasswordConfirm("12345");
		 Errors errors = new BeanPropertyBindingResult(user, "user");
		userValidator.validate(user, errors);
	//	userService= new UserServiceImpl();
		//userService.findByUsername("Tom");
		Mockito.when(userService.findByUsername("Tom"))
        .thenReturn(user);
		
		//User user= new User();
		String str = uc.registration( user, bindingResult);
	
		
	assertEquals("redirect:/welcome", str);
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	@Test
	public void testBookingAction() {
		try {
			UserController uc = new UserController();
			Model model = new ConcurrentModel();
			BookingService bookingService= new BookingService();
			bookingService.updateBooking("APPROVED", 1L);
			
			//when(bookingService.updateBooking(Mockito.anyString(), Mockito.anyLong())).thenReturn(1);
			String s= uc.bookingAction(model,request,response,"APPROVED",1);
			
			assertEquals("booking", s);
		}catch(Exception e) {
			
		}
		
	}
	@Test
	public void testBookingActionRject() {
		try {
			UserController uc = new UserController();
			Model model = new ConcurrentModel();
			BookingService bookingService= new BookingService();
			bookingService.updateBooking("REJECT", 1L);
			
			//when(bookingService.updateBooking(Mockito.anyString(), Mockito.anyLong())).thenReturn(1);
			String s= uc.bookingAction(model,request,response,"REJECT",1);
			
			assertEquals("booking", s);
		}catch(Exception e) {
			
		}
		
	}
	
	@Test
	public void testBookingActionDelete() {
		try {
			UserController uc = new UserController();
			Model model = new ConcurrentModel();
			BookingService bookingService= new BookingService();
			bookingService.updateBooking("delete", 1L);
			
			//when(bookingService.updateBooking(Mockito.anyString(), Mockito.anyLong())).thenReturn(1);
			String s= uc.bookingAction(model,request,response,"delete",1);
		
			assertEquals("Tom", s);
		}catch(Exception e) {
			
		}
		
	}
	
	
}
