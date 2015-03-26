package com.glomozda.test.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glomozda.test.jpa.domain.Product;

@Service
public class ProductService {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Product> getAll() {
		List<Product> result = em.createNamedQuery("Product.findAll", Product.class).getResultList();
		return result;
	}
	
	@Transactional
	public void add(Product p) {
		em.persist(p);
	}
}
