package com.MySpringbootDemo.Controllers;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
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
	
	@RequestMapping("/product-listing")
	public String index() {
		return "product-listing";
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
		System.out.println(product.toString()); 
		//product.setId(0l);
		
		
//		if(!product.getImage().startsWith("/uploads")) {
//			
//			//pozriet sa ci existuje obrazok v zlozke /uploads/(product id)/image.jpg
//			//ak áno rename na /uploads/(product id)/image-old.jpg
//			//vytvorit obrazok do /uploads/(product id)/image.jpg
//			
//		}
		
		
		
		
		product = productDao.save(product);
		return product.getProductId().toString();
	}

	@RequestMapping(value = "/hideproduct/{id}", method = RequestMethod.POST)
	@ResponseBody
	public void hideProduct(@PathVariable(value = "id") Long id) {
		Product product = productDao.getProductById(id);
		product.setMetaActive(0);
		productDao.save(product);
	}
	
	@RequestMapping(value = "/activeproducts", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> ActiveProductsList() {
		List<Product> products = productDao.findByMetaActive(1);
		//return products;
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

}