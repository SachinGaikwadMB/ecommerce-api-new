package com.mb.api.persistance.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mb.api.persistance.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{
	Optional<Product> findByCode(String code);
	
	List<Product>  findByNameOrCategory(String name, String category);
	
	@Query(value = "SELECT p FROM Product AS p WHERE p.brand LIKE :searchText")
	List<Product> filterProductsData(@Param("searchText") String searchText);
	
	
}
