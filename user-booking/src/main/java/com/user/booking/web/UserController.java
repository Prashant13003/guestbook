package com.user.booking.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.user.booking.CustomFileValidator;
import com.user.booking.model.Booking;
import com.user.booking.model.BookingForm;
import com.user.booking.model.Role;
import com.user.booking.model.User;
import com.user.booking.repository.BookingRepository;
import com.user.booking.repository.RoleRepository;
import com.user.booking.service.BookingService;
import com.user.booking.service.SecurityService;
import com.user.booking.service.UserService;
import com.user.booking.validator.UserValidator;

/**
 * 
 * Controller class for All Request
 *
 */
@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	CustomFileValidator customFileValidator;
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private BookingService bookingService;

	@Autowired
	private UserValidator userValidator;

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER");
		roles.add(userRole);
		userForm.setRoles(roles);

		userService.save(userForm);

		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/welcome";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@GetMapping({ "/", "/welcome" })
	public String welcome(Model model, HttpServletRequest request, HttpServletResponse response) {

		model.addAttribute("roleName", getUserRole(request, response));
		return "welcome";
	}

	@GetMapping({ "/home" })
	public String home(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("roleName", getUserRole(request, response));
		return "welcome";
	}

	@GetMapping("/booking")
	public String getbooking(Model model, HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bookingForm") BookingForm booking, BindingResult bindingResult) {
		model.addAttribute("roleName", getUserRole(request, response));
		model.addAttribute("bookingForm", new BookingForm());
		return "booking";
	}

	@GetMapping("/viewBooking")
	public String viewBooking(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("roleName", getUserRole(request, response));
		List<Booking> bookinglistService = bookingRepository.findbyStatus("SUBMITED");

		List<BookingForm> bookingFormLst = new ArrayList<>();
		for (Booking booking : bookinglistService) {
			BookingForm obj = new BookingForm();
			obj.setBookingDate(booking.getBookingDate() + "");
			obj.setEmail(booking.getEmail());
			obj.setId(booking.getId());
			if (booking.getComments() != null) {
				obj.setComments(booking.getComments());
			}
			if (booking.getFile() != null && booking.getFile().length > 0) {
				obj.setDownloadUrl("EXIST");
			} else {
				obj.setDownloadUrl("NOTEXIST");
			}
			bookingFormLst.add(obj);

		}

		model.addAttribute("bookingForm", new BookingForm());
		model.addAttribute("bookinglist", bookingFormLst);
		return "viewBooking";
	}

	@RequestMapping(value = { "/download/{id}" }, method = RequestMethod.GET)
	public void downloadDocument(@PathVariable Long id, HttpServletResponse response) throws IOException {
		Booking booking = bookingRepository.findbyId(id);

		byte[] file = booking.getFile();
		String fileName = "download.png";
		response.setContentType("image/png; name =" + fileName);
		response.setContentLength(file.length);
		response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.getOutputStream().write(file);
		response.getOutputStream().flush();

	}

	@PostMapping(path = "/booking")
	public String booking(Model model, HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bookingForm") BookingForm booking, BindingResult bindingResult) {

		// model.addAttribute("roleName", getUserRole(request, response));

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String roleName = null;
		String currentPrincipalName = null;
		if (authentication != null) {
			currentPrincipalName = authentication.getName();
			User userTemp = userService.findByUsername(currentPrincipalName);
			logger.info("role--" + userTemp.getRoles());
			for (Role role : userTemp.getRoles()) {
				roleName = role.getName();

			}

		}
		model.addAttribute("roleName", roleName);

		// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		// Parsing the given String to Date object
		// Date date;
		Booking bookingTemp = new Booking();
		customFileValidator.validate(booking, bindingResult);
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			for (FieldError error : errors) {
				logger.info(error.getObjectName() + " - " + error.getDefaultMessage());
				model.addAttribute("errormessage", "Please upload only image/png file");
			}
			return "booking";
		}

		try {
			if (booking.getFile() != null) {
				MultipartFile multipartFile = (MultipartFile) booking.getFile();
				bookingTemp.setFile(multipartFile.getBytes());
			}
			// date = formatter.parse(""+new Date());
			if (booking.getComments() != null) {
				bookingTemp.setComments(booking.getComments());
			}

			bookingTemp.setBookingDate(new Date());
			// bookingTemp.setEmail(currentPrincipalName);
			// bookingTemp.setName("test_"+currentPrincipalName);
			// bookingTemp.setPhone("011122334");
			bookingTemp.setStatus("SUBMITED");// REJECTED,APPROVED,SUBMITED
			bookingRepository.save(bookingTemp);
			model.addAttribute("successmessage", "User Entry Submitted successfully!!!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "booking";
	}

	@GetMapping("/bookingAction/{id}")
	public String bookingAction(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam String action, @PathVariable Integer id) {
		// model.addAttribute("userForm", new User());
		model.addAttribute("roleName", getUserRole(request, response));
		Long longId = Long.valueOf(id);

		if (action.equalsIgnoreCase("approve")) {
			bookingService.updateBooking("APPROVED", longId);

		} else if (action.equalsIgnoreCase("reject")) {
			bookingService.updateBooking("REJECTED", longId);

		} else if (action.equalsIgnoreCase("delete")) {
			bookingService.deleteBooking(longId);

		}

		List<Booking> bookinglist = bookingRepository.findbyStatus("SUBMITED");
		model.addAttribute("bookingForm", new BookingForm());
		model.addAttribute("bookinglist", bookinglist);
		return "booking";
	}

	public String getUserRole(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String roleName = null;
		if (authentication != null) {
			String currentPrincipalName = authentication.getName();
			User userTemp = userService.findByUsername(currentPrincipalName);
			logger.info("role--" + userTemp.getRoles());
			for (Role role : userTemp.getRoles()) {
				roleName = role.getName();

			}

		}
		return roleName;
	}

}
