package com.glomozda.test.jpa.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {
	@Id
	@GeneratedValue
	@Column(name = "order_items_id")
	private Integer orderItemId;
	
	@Column(name = "amount")	
	private Integer orderItemAmount;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "product_id")	
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "order_id")	
	private Order order;
	
	public OrderItem() {		
	}
	
	public OrderItem(final Product product, final int amount) {
		this.product = product;
		this.orderItemAmount = amount;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(final Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getOrderItemAmount() {
		return orderItemAmount;
	}

	public void setOrderItemAmount(final Integer orderItemAmount) {
		this.orderItemAmount = orderItemAmount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(final Product product) {
		this.product = product;
		if (!product.getOrderItems().contains(this)) {
            product.getOrderItems().add(this);
        }
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(final Order order) {
		this.order = order;
		if (!order.getOrderItems().contains(this)) {
            order.getOrderItems().add(this);
        }
	}
	
	@Override
	public String toString() {		
		return "orderItem{" + "orderItemId=" + orderItemId + 
				", order=" + order.getOrderName() + 
				", product=" + product.getProductName() + 
				", amount=" + orderItemAmount + '}'+"\n";
	}
}
