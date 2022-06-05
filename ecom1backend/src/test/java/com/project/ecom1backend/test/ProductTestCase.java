package com.project.ecom1backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.project.ecom1backend.dao.ProductDAO;
import com.project.ecom1backend.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;
	
	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("com.project.ecom1backend");
		context.refresh();
		
		productDAO = (ProductDAO) context.getBean("productDAO");
}
//	
//	@Test
//	public void testProductCRUD() {
//		// Adding
//		product = new Product();
//		product.setName("Redmi Note 4");
//		product.setBrand("Redmi");
//		product.setDescription("ching chung phone");
//		product.setUnitPrice(40000);
//		product.setActive(true);
//		product.setCategoryId(3);
//		product.setSupplierId(3);
//
//		assertEquals("Could not add", true, productDAO.add(product));
//
//		// Fetching/Updating
//
//		product = productDAO.get(2);
//		product.setName("MoreRedmi");
//		assertEquals("Could not fetch", true, productDAO.update(product));
//
//		// Deleting
//		assertEquals("Could not delete", true, productDAO.delete(product));
//		
//		//List
//		assertEquals("Could not fetch the list", 6, productDAO.list().size());
//	}
	
	@Test
	public void testLiveActiveProducts() {
		assertEquals("Could not fetch the list", 5, productDAO.listActiveProducts().size());
		
	}

	@Test
	public void testlistActiveProductsByCategory() {
		assertEquals("Could not fetch the list", 2, productDAO.listActiveProductsByCategory(1).size());
		
	}
	
	@Test
	public void testLatestActiveProducts() {
		assertEquals("Could not fetch the list", 3, productDAO.getLatestActiveProducts(3).size());
		
	}
}
