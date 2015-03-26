package com.glomozda.test.jpa;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.glomozda.test.jpa.domain.Order;
import com.glomozda.test.jpa.domain.OrderItem;
import com.glomozda.test.jpa.domain.Product;
import com.glomozda.test.jpa.repository.OrderItemService;
import com.glomozda.test.jpa.repository.OrderService;
import com.glomozda.test.jpa.repository.ProductService;

public class TestJPAService {
	
	@Autowired
	private transient OrderService orderService;
	
	@Autowired
	private transient OrderItemService orderItemService;
	
	@Autowired
	private transient ProductService productService;
	
	@Autowired
    protected JdbcTemplate jdbcTemplate;
	
	public String sayHello() {
		return "Hello!";
	}
	
	@Transactional
	private void prepareDB() {
		
		Calendar calendar = Calendar.getInstance();
		
		jdbcTemplate.execute("TRUNCATE TABLE Orders");
        jdbcTemplate.execute("ALTER TABLE Orders ALTER COLUMN orders_id RESTART WITH 1");
        
        jdbcTemplate.execute("TRUNCATE TABLE Order_Items");
        jdbcTemplate.execute("ALTER TABLE Order_Items ALTER COLUMN order_items_id RESTART WITH 1");
		
		jdbcTemplate.execute("TRUNCATE TABLE Products");
        jdbcTemplate.execute("ALTER TABLE Products ALTER COLUMN products_id RESTART WITH 1");
        
        final Order o1 = new Order();
        o1.setOrderName("O-1");
        o1.setOrderDate(new Date(calendar.getTimeInMillis()));
        orderService.add(o1);
                
        final Order o2 = new Order();
        o2.setOrderName("O-2");
        o2.setOrderDate(new Date((long) (calendar.getTimeInMillis() * 0.9)));
        orderService.add(o2);
        
        final Product p1 = new Product();
        p1.setProductName("Computer");
        productService.add(p1);
        
        final OrderItem oi1 = new OrderItem();
        oi1.setOrderItemAmount(10);
        orderItemService.add(oi1, 1, 1);
	}
	
	@Transactional
	public void testDB() {
		prepareDB();
		
		List<Product> productsList = productService.getAll();
		
		for (Product p : productsList) {
			System.out.println(p.toString());
		}
		
		List<Order> ordersList = orderService.getAll();
		
		for (Order o : ordersList) {
			System.out.println(o.toString());
		}
		
		List<OrderItem> orderItemsList = orderItemService.getAllWithFetching();
		
		for (OrderItem oi : orderItemsList) {
			System.out.println(oi.toString());
		}
		
		System.out.println("========================");
		System.out.println("NOW WITH CASCADE PERSIST");
		System.out.println("========================");
		
		orderService.addWithCascade(1, 25);
		
		ordersList = orderService.getAll();
		
		for (Order o : ordersList) {
			System.out.println(o.toString());
		}
		
		orderItemsList = orderItemService.getAllWithFetching();
		
		for (OrderItem oi : orderItemsList) {
			System.out.println(oi.toString());
		}
	}
}
