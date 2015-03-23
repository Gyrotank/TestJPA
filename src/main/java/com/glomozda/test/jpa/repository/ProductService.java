package com.glomozda.test.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glomozda.test.jpa.domain.Product;

@Service
public class ProductService {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Product> getAll() {
		List<Product> result = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
		return result;
	}
	
	@Transactional
	public Product getProductById(int id) {
		Product result = null; 
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p"
				+ " WHERE p.productId = :id", Product.class);
		query.setParameter("id", id);	  
		try {
			result = query.getSingleResult();
		} catch (NoResultException nre){}
		
		return result;
	}
	
	@Transactional
	public void add(Product p) {
		em.persist(p);
	}
}
