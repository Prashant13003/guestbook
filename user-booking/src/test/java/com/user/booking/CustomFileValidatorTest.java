package com.user.booking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import com.user.booking.model.BookingForm;

@RunWith(SpringRunner.class)
public class CustomFileValidatorTest {
	
	
	
	
	@MockBean
	private Errors errors;
	@MockBean
	private MultipartFile file;
	
	
	@Test
	public void testValidate() {
		CustomFileValidator customFileValidator= new CustomFileValidator();
		try {
			
			BookingForm bookingform= new BookingForm();
			
			BookingForm fileUploadModel =  new BookingForm();		
	        MultipartFile files = fileUploadModel.getFile();
			bookingform.setId(1L);
			bookingform.setFile(files);
		
			
			//Errors errors=(Errors) RETURNS_SELF;  
			//errors.rejectValue("name", "Size.booking.name");
			customFileValidator.validate(bookingform,  errors);
			
		}catch(Exception e) {
		
		}
		
	}

}
