package com.smartsys;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.smartsys.entities.Product;
import com.smartsys.repository.ProductRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository) {
		return args -> {
	
			productRepository.save(Product.builder()
										.name("APPlE M3 MAX")
										.price(5000.0)
										.build());

			productRepository.save(Product.builder()
										.name("iPhone 15 Pro")
										.price(1300.0)
										.build());
			
			productRepository.save(Product.builder()
										.name("OpenAI")
										.price(30.0)
										.build());

			productRepository.save(Product.builder()
										.name("Github Copilot")
										.price(20.0)
										.build());

			productRepository.findAll().forEach(System.out::println);
		};
	}

}
