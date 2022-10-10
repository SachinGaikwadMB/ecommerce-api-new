package com.mb.api.web.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mb.api.business.services.ProductService;
import com.mb.api.persistance.entity.Product;
import com.mb.api.web.model.SaveProductModel;
import static com.mb.api.constants.GenericConstanst.PRODUCT;

@RestController
@RequestMapping(PRODUCT)
public class ProductController extends BaseController
{
	@Autowired
	private ProductService productService;

	@PreAuthorize("hasRole('USER')")
	@GetMapping
	public ResponseEntity<List<Product>> getAllProductsData()
	{
		return new ResponseEntity<>(productService.getAllProductsData(), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Object> saveProduct(@RequestBody @Valid SaveProductModel saveProductModel)
	{
		return new ResponseEntity<>(productService.saveProduct(saveProductModel), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{id}")
	public ResponseEntity<Object> getProductById(@PathVariable(name = "id") Integer id)
	{
		return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{code}")
	public ResponseEntity<Object> updateProductByCode(@PathVariable(name = "code") String code, @RequestBody @Valid SaveProductModel saveProductModel)
	{
		return new ResponseEntity<>(productService.updateProductByCode(code, saveProductModel), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProductById(@PathVariable(name = "id") Integer id)
	{
		return new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/search")
	public ResponseEntity<Object> searchByNameOrCategory(@RequestParam(name = "name") String name, @RequestParam(name = "category") String category)
	{

		return new ResponseEntity<>(productService.searchByNameOrCategory(name, category), HttpStatus.OK);
	}

	@GetMapping("/sort")
	public ResponseEntity<Object> filterProductsData(@RequestParam(name = "searchText", required = true, defaultValue = "Xiome") String searchText)
	{
		return new ResponseEntity<>(productService.filterProductData(searchText), HttpStatus.OK);
	}

}
