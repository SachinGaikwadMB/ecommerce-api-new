package com.mb.api.web.controller;

import static com.mb.api.constants.GenericConstanst.CART;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mb.api.business.services.CartService;
import com.mb.api.web.model.AddToCartModel;

@RestController
@RequestMapping(CART)
public class CartController extends BaseController
{
	@Autowired
	private CartService cartService;
	
	
	@PostMapping
	public ResponseEntity<Object> addToCart(@RequestBody @Valid	 AddToCartModel addToCartModel)
	{
		return new ResponseEntity<>(cartService.addProductToCart(addToCartModel), HttpStatus.OK);
	}
}
