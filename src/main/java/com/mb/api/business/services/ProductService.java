package com.mb.api.business.services;

import java.util.List;
import com.mb.api.persistance.entity.Product;
import com.mb.api.web.model.SaveProductModel;

public interface ProductService
{
	List<Product> getAllProductsData();
	
	Object saveProduct(SaveProductModel saveProductModel);
	
	Object getProductById(Integer id);
	
	Object updateProductByCode(String code, SaveProductModel saveProductModel);
	
	Object deleteProductById(Integer id);
	
	Object searchByNameOrCategory(String name, String category);
	
	Object filterProductData(String searchText);
	
}
