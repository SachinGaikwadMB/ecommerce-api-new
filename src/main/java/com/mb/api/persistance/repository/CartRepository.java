package com.mb.api.persistance.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.mb.api.persistance.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>
{
	 @Query(value = "SELECT c FROM Cart c WHERE c.user.id =:id")
	 List<Cart> fetchDataFromCart(Integer id);
}
