package com.MySpringbootDemo.Repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MySpringbootDemo.Models.Product;


@Repository
@Transactional
public interface ProductDao extends JpaRepository<Product, Long> {


  /**
   * This method will find an User instance in the database by its email.
   * Note that this method is not implemented and its working code will be
   * automagically generated from its signature by Spring Data JPA.
   */
	//public Product findOne(long id);
	public Product getProductById(Long id);
	//List<Product> findByName(String name);
	
	public List<Product> findByMetaActive(int metaActive);


//public Product findOne(Long id);


  

}
