package com.project.ecom1backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.project.ecom1backend.dao.UserDAO;
import com.project.ecom1backend.dto.Address;
import com.project.ecom1backend.dto.Cart;
import com.project.ecom1backend.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;

	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("com.project.ecom1backend");
		context.refresh();

		userDAO = (UserDAO) context.getBean("userDAO");
	}

//	@Test
//	public void testAdd() {
//
//		user = new User();
//		user.setFirstName("me");
//		user.setLastName("melast");
//		user.setEmail("me@gmail");
//		user.setContactNumber("12345");
//		user.setRole("User");
//		user.setPassword("124");
//
//		assertEquals("Failed to add user!", true, userDAO.addUser(user));
//
//		address = new Address();
//		address.setAddressLineOne("meaddress1");
//		address.setAddressLineTwo("meaddress2");
//		address.setCity("mecity");
//		address.setState("mestate");
//		address.setCountry("mecountry");
//		address.setPostalCode("7100");
//		address.setBilling(true);
//
//		address.setUserId(user.getId());
//
//		assertEquals("Failed to add address!", true, userDAO.addAddress(address));
//
//		if (user.getRole().equals("USER")) {
//
//			cart = new Cart();
//			cart.setUser(user);
//
//			assertEquals("Failed to add cart!", true, userDAO.updateCart(cart));
//			
//			
//
//		}
		
		
//	}

}
