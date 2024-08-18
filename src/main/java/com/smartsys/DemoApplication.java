package com.smartsys;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.smartsys.entities.Product;
import com.smartsys.entities.Todo;
import com.smartsys.repository.ProductRepository;
import com.smartsys.repository.TodoRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository, TodoRepository todoRepository) {
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

			todoRepository.save(Todo.builder()
											.title("Training Prometheus")
											.createdAt(new Date())
											.completed(false)
											.build());

			todoRepository.save(Todo.builder()
											.title("Watch Prometheus Movie")
											.createdAt(new Date())
											.completed(false)
											.build());
			
			todoRepository.save(Todo.builder()
											.title("Play musique")
											.createdAt(new Date())
											.completed(false)
											.build());
			
			todoRepository.findAll().forEach(System.out::println);
			productRepository.findAll().forEach(System.out::println);

		};
	}

}
