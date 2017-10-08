package com.MySpringbootDemo.Controllers;

import java.util.List;
import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MySpringbootDemo.Models.Book;

@RestController
public class BooksController {
	@GetMapping("/books")
	
	public List<Book> getAllBooks(){
		return Arrays.asList(
				new Book(1l , "Mastering Spring", "some author"));
	}

}
