package com.user.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.user.booking.model.BookingForm;

/**
 * 
 * This File is responsible for validation file ie image/png which will be
 * uploaded.
 *
 */
@Component
public class CustomFileValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(CustomFileValidator.class);
	public static final String PNG_MIME_TYPE = "image/png";
	public static final long TEN_MB_IN_BYTES = 10485760;

	@Override
	public boolean supports(Class<?> clazz) {
		return BookingForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		try {
			BookingForm fileUploadModel = (BookingForm) target;
			MultipartFile file = fileUploadModel.getFile();
			logger.info("File " + file);
			if (!file.isEmpty()) {
				// errors.rejectValue("file", "upload.file.required");
				if (!PNG_MIME_TYPE.equalsIgnoreCase(file.getContentType())) {
					errors.rejectValue("file", "Please upload valid .png file only");
				}

				if (file.getSize() > TEN_MB_IN_BYTES) {
					errors.rejectValue("file", "Please upload a file with size less than 10 MB");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}