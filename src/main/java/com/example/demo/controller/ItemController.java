package com.example.demo.controller;

import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.model.Item;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    // 全アイテムを取得
    @GetMapping("/items/")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    // アイテムを取得
    @GetMapping("/items/{itemId}")
    public Item getItem(@PathVariable("itemId") Long itemId) {
        return itemService.getItem(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));
    }

    // アイテムを追加
    @PostMapping("/items/")
    public void addItem(@RequestBody Item item) {
        itemService.addItem(item);
    }

    // アイテムを更新
    @PutMapping("/items/{itemId}")
    public void updateItem(@RequestBody Item item, @PathVariable("itemId") Long itemId) {
        itemService.updateItem(item, itemId);
    }

    // アイテムを削除
    @DeleteMapping("/items/{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
    }

}
