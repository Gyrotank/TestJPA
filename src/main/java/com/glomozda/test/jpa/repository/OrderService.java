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
public class OrderService {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Order> getAll() {
		List<Order> result = em.createNamedQuery("Order.findAll", Order.class).getResultList();
		return result;
	}
	
	@Transactional
	public void add(Order o) {
		em.persist(o);
	}
	
	@Transactional
	public void addWithCascade(int productId, int amount) {
		Order newOrder = new Order();
		//newOrder.addOrderItem(new OrderItem(product, amount));
		newOrder.getOrderItems().add(new OrderItem(em.find(Product.class, productId), amount));
		em.persist(newOrder);
	}
}
