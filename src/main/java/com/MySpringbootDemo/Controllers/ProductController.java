package com.MySpringbootDemo.Controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.MySpringbootDemo.Models.Product;
import com.MySpringbootDemo.Repositories.ProductDao;

@Controller
public class ProductController {

	@Autowired
	private ProductDao productDao;

	@RequestMapping("/product/{id}")
	public String getProduct(@PathVariable(value = "id") Long id, Model model) {
		model.addAttribute("product", productDao.getProductById(id));
		return "product";
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String productsList(Model model) {
		model.addAttribute("products", productDao.findAll());
		return "products";
	}
	

	@RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
	@ResponseBody
	public String saveProduct(@RequestBody Product product) {
		productDao.save(product);
		return product.getProductId().toString();
	}

	@RequestMapping(value = "/view", produces = { MediaType.TEXT_HTML_VALUE }, method = RequestMethod.GET)
	public String productsList() {
		return "products";
	}
	
	@RequestMapping("/product-listing")
	public String index() {
	    return "product-listing";
	}
}