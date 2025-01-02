package com.example.demo;

import com.example.demo.model.Item;
import com.example.demo.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RestapiApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RestapiApplication.class);
	}

	@Autowired
	private ItemRepository itemRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestapiApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		itemRepository.save(new Item("Item 1", "Category 1"));
		itemRepository.save(new Item("Item 2", "Category 2"));
		itemRepository.save(new Item("Item 3", "Category 3"));
		itemRepository.save(new Item("Item 4", "Category 4"));
		itemRepository.save(new Item("Item 5", "Category 5"));
	}
}
