package com.mb.api.business.services;

import com.mb.api.web.model.AddToCartModel;

public interface CartService
{
	Object addProductToCart(AddToCartModel addToCartModel);
}
