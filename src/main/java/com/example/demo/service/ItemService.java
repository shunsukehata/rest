package com.example.demo.service;

import com.example.demo.model.Item;
import com.example.demo.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Cacheable("getItems")
    // 全アイテムを取得
    public List<Item> getAllItems() {
        List<Item> allItems = new ArrayList<>();
        // 3秒待機して検索を行う、キャッシュが有効になっていると3秒待機しない
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        itemRepository.findAll().forEach(allItems::add);
        return allItems;
    }

    // keyは#p0でもOK
    @Cacheable(value="getItem", key="#p0")
    // アイテムを取得
    public Optional<Item> getItem(Long itemId) {
        // 3秒待機して検索を行う、キャッシュが有効になっていると3秒待機しない
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return itemRepository.findById(itemId);
    }

    @CacheEvict(value="getItems", allEntries=true)
    // アイテムを追加
    public void addItem(Item item) {
        itemRepository.save(item);
    }

    @Caching(evict = {
            @CacheEvict(value="getItems", allEntries=true),
            @CacheEvict(value="getItem", key="#p0")
    })
    //  アイテムを更新
    public void updateItem(Item item, Long itemId) {
        if(itemRepository.findById(itemId).get() != null) {
            itemRepository.save(item);
        }
    }

    @Caching(evict = {
            @CacheEvict(value="getItems", allEntries=true),
            @CacheEvict(value="getItem", key="#p0")
    })
    //  アイテムを削除
    public void deleteItem(Long itemId) {
       itemRepository.deleteById(itemId);
    }
}

