package com.example.demo.controller;

import com.example.demo.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    private MockMvc mvc;

    // 検索結果が想定通りであるのかを検証するテスト
    @Test
    void getItem() throws Exception {
        int itemId = 1;
        String responseJsonString = mvc.perform(get("/items/" + itemId))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
//                .content(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE) //非推奨ではあるが、テストのために使用
//                .characterEncoding("UTF-8")

        // 取得したJsonオブジェクトをjavaオブジェクトに変換
        ObjectMapper objectMapper = new ObjectMapper();
        Item responseItem = objectMapper.readValue(responseJsonString, Item.class);

        // 検証
        assertThat(responseItem.getItemId()).isEqualTo(1L);
        assertThat(responseItem.getItemName()).isEqualTo("Item 1");
        assertThat(responseItem.getItemCategory()).isEqualTo("Category 1");

    }

    @Test
    void addItem() throws Exception {
        // テスト用のItemオブジェクトを作成
        Item newItem = new Item();
        newItem.setItemId(2L);
        newItem.setItemName("New Item");
        newItem.setItemCategory("New Category");

        // テスト用のItemオブジェクトをJsonに変換
        ObjectMapper objectMapper = new ObjectMapper();
        String newItemJson = objectMapper.writeValueAsString(newItem);

        // テスト用のItemオブジェクトを登録
        mvc.perform(post("/items/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newItemJson))
                .andExpect(status().isOk());

        // 検証
        String responseJsonString = mvc.perform(get("/items/" + newItem.getItemId()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Item responseItem = objectMapper.readValue(responseJsonString, Item.class);
        assertThat(responseItem.getItemId()).isEqualTo(newItem.getItemId());
        assertThat(responseItem.getItemName()).isEqualTo(newItem.getItemName());
        assertThat(responseItem.getItemCategory()).isEqualTo(newItem.getItemCategory());
    }

}