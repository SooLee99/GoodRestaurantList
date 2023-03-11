package com.example.restaurant.wishlist.repository;

import com.example.restaurant.db.MemoryDbRepositoryAbstract;
import com.example.restaurant.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository // 루트 컨테이너에 빈(Bean) 객체로 생성하는 어노테이션.
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {

}
