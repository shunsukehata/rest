package com.example.demo.repo;

import com.example.demo.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    // 裏でフレームワークが実装してくれる
    // このインターフェースを継承するだけで、データベースの操作が可能になる
    // saveメソッド、findByIdメソッド、findAllメソッド、deleteメソッドなどが使える
}
