package org.sid.orderservice;

import org.sid.orderservice.entities.Order;
import org.sid.orderservice.entities.ProductItem;
import org.sid.orderservice.enums.OrderStatus;
import org.sid.orderservice.model.Customer;
import org.sid.orderservice.model.Product;
import org.sid.orderservice.repositories.OrderRepository;
import org.sid.orderservice.repositories.ProductItemRepository;
import org.sid.orderservice.service.CustomerRestClientService;
import org.sid.orderservice.service.InventoryRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {



	public static void main(String[] args) {

		SpringApplication.run(OrderServiceApplication.class, args);

	}



	@Bean
	CommandLineRunner start(OrderRepository orderRepository,
							ProductItemRepository productItemRepository,
							CustomerRestClientService customerRestClientService,
							InventoryRestClientService inventoryRestClientService){
		return args -> {

			List<Customer> customers=customerRestClientService.allCustomer().getContent().stream().toList();
			List<Product> products=inventoryRestClientService.allProduct().getContent().stream().toList();
			Long customerId=1L;
			customerRestClientService.customerById(customerId);
			Random random =new Random();
			for(int i=0;i<20;i++){
				Order order= Order.builder()
						.customerId(random.nextLong(customers.size()-1))
						.status(Math.random()>0.5? OrderStatus.PENDING:OrderStatus.CREATED)
						.createdAt(new Date())
						.build();

				Order savedOrder=orderRepository.save(order);

				for(int j=0;j< products.size();j++){
					if(Math.random()>0.70)
					{
						ProductItem productItem=ProductItem.builder()
								.order(savedOrder)
								.productId(products.get(j).getId())
								.price(products.get(j).getPrice())
								.quantity(products.get(j).getQuantity())
								.discount(Math.random())
								.build();
						productItemRepository.save(productItem);
					}

				}

			}

		};

	}
}
