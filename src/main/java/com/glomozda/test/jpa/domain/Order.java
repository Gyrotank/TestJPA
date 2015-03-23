package com.glomozda.test.jpa.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue
	@Column(name = "orders_id")
	private Integer orderId;
	
	@Column(name = "order_date")	
	private Date orderDate;
	
	@Column(name = "name")	
	private String orderName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="order", cascade = CascadeType.PERSIST)
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	public Order() {
		orderName = "<noname>";
		orderDate = new Date(Calendar.getInstance().getTimeInMillis());
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(final Integer orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(final Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(final String orderName) {
		this.orderName = orderName;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void addOrderItem(final OrderItem orderItem) {
		this.orderItems.add(orderItem);
        if (orderItem.getOrder() != this) {
            orderItem.setOrder(this);
        }
	}
	
	@Override
	public String toString() {		
		return "order{" + "orderId=" + orderId + ", orderName=" + orderName + 
				", orderDate=" + orderDate.toString() + '}'+"\n";
	}
}
