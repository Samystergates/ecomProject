package com.project.ecom1backend.dao;

import java.util.List;

import com.project.ecom1backend.dto.Address;
import com.project.ecom1backend.dto.Cart;
import com.project.ecom1backend.dto.User;

public interface UserDAO {
	// user related operation
	User getByEmail(String email);
	User get(int id);

	boolean add(User user);
	boolean update(User user);
	// adding and updating a new address
	Address getAddress(int addressId);
	boolean addAddress(Address address);
	boolean updateAddress(Address address);
	Address getBillingAddress(int userId);
	List<Address> listShippingAddresses(int userId);
	
	
}
