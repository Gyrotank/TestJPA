package com.glomozda.test.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	public Product getProductByNameWithCriteriaAPI(String name) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
		Root<Product> from = criteriaQuery.from(Product.class);
		CriteriaQuery<Object> select = criteriaQuery.select(from);
		 
		Expression<String> literal = criteriaBuilder.upper(criteriaBuilder.literal((String) name));
		Predicate predicate = criteriaBuilder.like(criteriaBuilder.upper(from.get("productName")), literal);
		 
		criteriaQuery.where(predicate);
		 
		TypedQuery<Object> typedQuery = em.createQuery(select);
		List<Object> resultList = typedQuery.getResultList();
		return (Product)resultList.get(0);
	}
	
	@Transactional
	public void add(Product p) {
		em.persist(p);
	}
}
