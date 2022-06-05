package com.project.ecom1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.ecom1.exception.ProductNotFoundException;
import com.project.ecom1backend.dao.CategoryDAO;
import com.project.ecom1backend.dao.ProductDAO;
import com.project.ecom1backend.dto.Category;
import com.project.ecom1backend.dto.Product;

import com.project.ecom1.controller.PageController;
import com.project.ecom1backend.dao.UserDAO;
import com.project.ecom1backend.dto.User;

import com.project.ecom1.model.UserModel;

@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index(@RequestParam(name = "logout", required = false) String logout) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");

		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");

		mv.addObject("categories", categoryDAO.list());

		if (logout != null) {
			mv.addObject("message", "You have successfully logged out!");
		}

		mv.addObject("userClickHome", true);
		return mv;
	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}

	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}


	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");

		mv.addObject("categories", categoryDAO.list());

		mv.addObject("userClickAllProducts", true);
		return mv;
	}

	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");

		Category category = null;

		category = categoryDAO.get(id);

		mv.addObject("title", category.getName());

		mv.addObject("categories", categoryDAO.list());

		mv.addObject("category", category);

		mv.addObject("userClickCategoryProducts", true);
		return mv;
	}


	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {

		ModelAndView mv = new ModelAndView("page");

		Product product = productDAO.get(id);

		if (product == null)
			throw new ProductNotFoundException();

		product.setViews(product.getViews() + 1);
		productDAO.update(product);

		mv.addObject("title", product.getName());
		mv.addObject("product", product);

		mv.addObject("userClickShowProduct", true);

		return mv;

	}

	@RequestMapping(value = "/membership")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("page");

		logger.info("Page Controller membership called!");

		return mv;
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("title", "Login");
		if (error != null) {
			mv.addObject("message", "Username and Password is invalid!");
		}
		if (logout != null) {
			mv.addObject("logout", "You have logged out successfully!");
		}
		return mv;
	}

	@RequestMapping("/editProfile")
	public ModelAndView editProfile(@ModelAttribute("userModel") UserModel userModel) {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Edit Profile");
		mv.addObject("userClickEditProfile", true);

		mv.addObject("user", userDAO.get(userModel.getId()));

		return mv;

	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profileDetails(@Valid @ModelAttribute("user") User eUser, Model model, HttpServletRequest request) {

		if (eUser.getId() == 0) {
			userDAO.add(eUser);
		} else {
			userDAO.update(eUser);
		}

		return "redirect:/home?success=user";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/login?logout";
	}

	@RequestMapping(value = "/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Aha! Caught You.");
		mv.addObject("errorDescription", "You are not authorized to view this page!");
		mv.addObject("title", "403 Access Denied");
		return mv;
	}

}
