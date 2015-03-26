package com.glomozda.test.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glomozda.test.jpa.domain.Order;
import com.glomozda.test.jpa.domain.OrderItem;
import com.glomozda.test.jpa.domain.Product;

@Service
public class OrderItemService {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<OrderItem> getAll() {
		List<OrderItem> result = em.createNamedQuery("OrderItem.findAll", OrderItem.class).getResultList();
		return result;
	}
	
	@Transactional
	public List<OrderItem> getAllWithFetching() {
		List<OrderItem> result = 
				em.createNamedQuery("OrderItem.findAllWithFetching", OrderItem.class).getResultList();
		return result;
	}
	
	@Transactional
	public void add(OrderItem oi, int orderId, int productId) {
		Order order = em.getReference(Order.class, orderId);
		Product product = em.getReference(Product.class, orderId);
		
		OrderItem newOrderItem = new OrderItem();
		newOrderItem.setOrder(order);
		newOrderItem.setProduct(product);
		newOrderItem.setOrderItemAmount(oi.getOrderItemAmount());
		em.persist(newOrderItem);
	}
}
