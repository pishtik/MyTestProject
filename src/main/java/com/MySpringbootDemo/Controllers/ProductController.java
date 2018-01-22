package com.MySpringbootDemo.Controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
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

import com.MySpringbootDemo.General.GlobalProperties;
import com.MySpringbootDemo.Models.Product;
import com.MySpringbootDemo.Repositories.ProductDao;

@Controller
public class ProductController {
	@Autowired
	private GlobalProperties globalProperties;
	
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
		// return products;
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
	@ResponseBody
	public String saveProduct(@RequestBody Product product) {
		System.out.println(product.toString());
		// product.setId(0l);

		if (product.getImage() != null) {
			Path origFile = Paths.get(globalProperties.getImageUploadBaseDir() + product.getId() + "/image.jpg");
			try {
				if (fileExists(origFile)) {
					renameFile(product.getId(), origFile);
				}
				createFile(product.getImage(), origFile);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

		product = productDao.save(product);
		return product.getProductId().toString();
	}

	private boolean fileExists(Path origFile) {
		return origFile.toFile().isFile();
	}

	private void renameFile(long productId, Path origFile) throws IOException {
		Path renamedFile = Paths.get(globalProperties.getImageUploadBaseDir() + productId + "/image-old.jpg");
		Files.move(origFile, renamedFile, StandardCopyOption.REPLACE_EXISTING);
	}

	private void createFile(String imageString, Path origFile) throws FileNotFoundException, IOException {
		String imageDataString = getImageDataString(imageString);
		byte[] imageByteArray = Base64.getDecoder().decode(imageDataString);
		origFile.toFile().getParentFile().mkdirs();
		FileOutputStream imageOutFile = new FileOutputStream(origFile.toFile());
		imageOutFile.write(imageByteArray);
		imageOutFile.close();
	}

	private String getImageDataString(String imageString) {
		return imageString.substring(imageString.indexOf(",") + 1);
	}

	@RequestMapping(value = "/hideproduct/{id}", method = RequestMethod.POST)
	@ResponseBody
	public  ResponseEntity<Product> hideProduct(@PathVariable(value = "id") Long id) {
		Product product = productDao.getProductById(id);
		product.setMetaActive(0);
		productDao.save(product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@RequestMapping(value = "/activeproducts", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> ActiveProductsList() {
		List<Product> products = productDao.findByMetaActive(1);
		for (Product product : products) {
			Path file = Paths.get(globalProperties.getImageUploadBaseDir() + product.getId() + "/image.jpg");
			if (file.toFile().exists()) {
				product.setImage("uploads/" + product.getId() + "/image.jpg");
			}
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

}