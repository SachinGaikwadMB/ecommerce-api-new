package com.mb.api.business.services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mb.api.business.exceptions.CustomException;
import com.mb.api.business.exceptions.ResourceAlreadyExistsException;
import com.mb.api.business.exceptions.ResourceNotFoundException;
import com.mb.api.persistance.entity.Product;
import com.mb.api.persistance.repository.ProductRepository;
import com.mb.api.web.model.SaveProductModel;

@Service
public class ProductServiceImpl implements ProductService
{
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Product> getAllProductsData()
	{
		try
		{

			return productRepository.findAll();
		}
		catch (Exception e)
		{
			throw new CustomException("Error :: Unable fetch data");
		}

	}

	@Override
	public Object saveProduct(SaveProductModel saveProductModel)
	{
		Optional<Product> optProduct = productRepository.findByCode(saveProductModel.getCode());

		if (optProduct.isPresent())
		{
			throw new ResourceAlreadyExistsException("Warning  :: Product Already Exist ! You can update the stocks only!");
		}

		try
		{
			Product product = modelMapper.map(saveProductModel, Product.class);
			logger.info("product is saving ...");
			productRepository.save(product);
			logger.info("product saved successfully!");
		}
		catch (Exception e)
		{
			throw new CustomException("Error :: Internal Server Error !");
		}

		return "product saved successfully!";
	}

	@Override
	public Object getProductById(Integer id)
	{
		Optional<Product> optProduct = productRepository.findById(id);

		if (optProduct.isEmpty())
		{
			throw new ResourceNotFoundException("Error :: Product not Found with ID :: " + id);
		}

		return optProduct.get();
	}

	@Override
	public Object updateProductByCode(String code, SaveProductModel saveProductModel)
	{
		Optional<Product> optProduct = productRepository.findByCode(code);
		if (optProduct.isEmpty())
		{
			throw new ResourceNotFoundException("Error :: Product not Found with code :: " + code);
		}

		Product productFromDB = optProduct.get();
		productFromDB.setName(saveProductModel.getName());
		productFromDB.setCode(saveProductModel.getCode());
		productFromDB.setCategory(saveProductModel.getCategory());
		productFromDB.setPrice(saveProductModel.getPrice());
		productFromDB.setBrand(saveProductModel.getBrand());
		productFromDB.setStockUnits(saveProductModel.getStockUnits());

		try
		{
			logger.info("Product is updating ...");
			productRepository.save(productFromDB);
			logger.info("Product is updated !");
		}
		catch (Exception e)
		{
			throw new CustomException("Error :: Error While updating product.");
		}

		return "Product Updated Successfully !";
	}

	@Override
	@Transactional
	public Object deleteProductById(Integer id)
	{
		Optional<Product> optProduct = productRepository.findById(id);

		if (optProduct.isEmpty())
		{
			throw new ResourceNotFoundException("Error :: Product not Found with ID :: " + id);
		}

		productRepository.deleteById(id);
		return "Product deleted successfully !";
	}

	@Override
	public Object searchByNameOrCategory(String name, String category)
	{
		
		return productRepository.findByNameOrCategory(name, category);
	}

	@Override
	public Object filterProductData(String searchText)
	{
	
		return productRepository.filterProductsData(searchText);
	}

}
