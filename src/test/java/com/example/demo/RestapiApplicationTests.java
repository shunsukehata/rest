package com.example.demo;

import com.example.demo.controller.ItemController;
import com.example.demo.repo.ItemRepository;
import com.example.demo.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RestapiApplicationTests {

	@Autowired
	private ItemController itemController;

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemRepository itemRepository;

	// アプリケーションがspring-boot-starter-testによって提供されるSpring Bootテスト機能を使用して正常に読み込まれるかどうかを確認
	@Test
	void contextLoads() {
		// assertThat()メソッドを使用して、itemController、itemService、itemRepositoryがnullでないことを確認
		assertThat(itemController).isNotNull();
		assertThat(itemService).isNotNull();
		assertThat(itemRepository).isNotNull();
	}

}
