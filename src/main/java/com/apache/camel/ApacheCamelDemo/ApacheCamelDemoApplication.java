package com.apache.camel.ApacheCamelDemo;

import com.apache.camel.ApacheCamelDemo.router.BookRoute;
import com.apache.camel.ApacheCamelDemo.router.EmployeeRoute;
import com.apache.camel.ApacheCamelDemo.service.BookService;
import com.apache.camel.ApacheCamelDemo.service.EmployeeService;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApacheCamelDemoApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(ApacheCamelDemoApplication.class, args);
		try (CamelContext camelContext = new DefaultCamelContext()) {

			camelContext.addRoutes(new EmployeeRoute());
			camelContext.getRegistry().bind("employeeService", new EmployeeService());
			camelContext.addRoutes(new BookRoute());
			camelContext.getRegistry().bind("bookService",new BookService());

			camelContext.start();
			Thread.sleep(3000000);
		}
	}

}


