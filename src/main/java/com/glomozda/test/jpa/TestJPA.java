package com.glomozda.test.jpa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJPA {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		TestJPAService service = (TestJPAService) context
				.getBean("testJPAService");
		String message = service.sayHello();
		System.out.println(message);
		
		service.testDB();
		
		((ConfigurableApplicationContext)context).close();
	}

}
