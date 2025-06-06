package com.sushanth.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
		var order = context.getBean(OrderServer.class);
		order.placeOrder(100);
		// var orderServer1 = new OrderServer(new StripePaymentService());
		// var orderServer2 = new OrderServer();
		// orderServer2.setPaymentService(new PayPalPaymentService());
		// orderServer1.placeOrder(100);
		// orderServer2.placeOrder(200);
	}

}
