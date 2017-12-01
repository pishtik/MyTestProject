package com.MySpringbootDemo.Controllers;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import javax.inject.Inject;
import com.MySpringbootDemo.Models.Product;
import com.MySpringbootDemo.Repositories.ProductDao;

@Controller
public class ProductController {
	@Inject
    ResourceUrlProvider resourceUrlProvider;

    @ModelAttribute("urls")
    public ResourceUrlProvider urls() {
        return this.resourceUrlProvider;
    }
	
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

	@RequestMapping(value = "/ajaxproducts", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> productsList1() {
		List<Product> products = productDao.findAll();
		//return products;
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
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
	@RequestMapping("/product-listing2")
	public String index1() {
		return "product-listing2";
	}
}