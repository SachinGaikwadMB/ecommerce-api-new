package com.mb.api.business.services;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mb.api.business.exceptions.CustomException;
import com.mb.api.persistance.entity.Cart;
import com.mb.api.persistance.repository.CartRepository;
import com.mb.api.persistance.repository.ProductRepository;
import com.mb.api.persistance.repository.UserRepository;
import com.mb.api.web.model.AddToCartModel;

@Service
public class CartServiceImpl implements CartService
{

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Object addProductToCart(AddToCartModel addToCartModel)
	{
		List<Cart> cartItems = cartRepository.fetchDataFromCart(addToCartModel.getUserId());

		for (int idx = 0; idx < cartItems.size(); idx++)
		{
			Cart cart = cartItems.get(idx);
			boolean isProductAdded = cart.getProducts().get(idx).getCode().equalsIgnoreCase(addToCartModel.getCode());
			if (isProductAdded)
			{
				throw new CustomException("Product already added");
			}

		}

		Cart cart = new Cart();
		cart.setQuantity(addToCartModel.getQuantity());
		cart.setProducts(Arrays.asList(productRepository.findByCode(addToCartModel.getCode()).get()));
		cart.setUser(userRepository.findById(addToCartModel.getUserId()).get());

		cartRepository.save(cart);
		return "Success :: Product added to cart !";
	}

}
