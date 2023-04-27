package org.sid.inventoryservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sid.inventoryservice.entities.Product;
import org.sid.inventoryservice.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.io.File;
import java.util.List;
import java.util.Random;

@SpringBootApplication
//@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){
		return args -> {
			/*Random random =new Random();
			for(int i=1;i<10;i++){
				productRepository.saveAll(List.of(
						Product.builder()
								.name("Computer"+i)
								.price(1200+Math.random()*10000)
								.quantity(1+random.nextInt(200))
								.build()
			));
			}*/

			restConfiguration.exposeIdsFor(Product.class);

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			List<Product> products = mapper.readValue(new File("C:\\Projects\\JavaProjects\\Ecommerce\\inventory-service\\src\\main\\java\\org\\sid\\inventoryservice\\products.json"), new TypeReference<List<Product>>(){});

			productRepository.saveAll(products);


		};
	}
}
