package com.mb.api.persistance.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "shopping_cart")

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Cart extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "product_quantity")
	private Integer quantity;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id")
	private List<Product> products;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id", unique = true)
	private User user;

}
