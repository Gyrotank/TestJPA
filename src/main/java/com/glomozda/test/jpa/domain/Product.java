package com.glomozda.test.jpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue
	@Column(name = "products_id")
	private Integer productId;
	
	@Column(name = "name")	
	private String productName;
	
	@OneToMany
	@JoinColumn(name = "product_id")
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	public Product(){		
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(final Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(final String productName) {
		this.productName = productName;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void addOrderItem(final OrderItem orderItem) {
        this.orderItems.add(orderItem);
        if (orderItem.getProduct() != this) {
            orderItem.setProduct(this);
        }
    }	
	
	@Override
	public String toString() {		
		return "product{" + "productId=" + productId + ", productName=" + productName + '}'+"\n";
	}
}
