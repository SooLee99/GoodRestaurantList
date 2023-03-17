package com.example.restaurant.wishlist.repository;

import com.example.restaurant.db.MemoryDbRepositoryAbstract;
import com.example.restaurant.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository // @Repository : 루트 컨테이너에 빈(Bean) 객체로 생성하는 어노테이션.
// WishListRepository 클래스 : WishListEntity 타입의 객체를 이용하여, MemoryDbRepositoryAbstract 추상 클래스를 사용하기 위한 클래스.
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {

}
