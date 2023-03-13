package com.example.restaurant.wishlist.repository;

import com.example.restaurant.wishlist.entity.WishListEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// MemoryDbRepositoryAbstract 추상클래스에서 WishListEntity 객체가 재대로 동작하는지 테스트하는 클래스.
@SpringBootTest
public class WishListRepositoryTest {

    // @Autowired : 스프링 DI(Dependency Injection)에서 사용되는 어노테이션.
    // 스프링에서 빈 인스턴스가 생성된 이후, @Autowired를 설정한 메서드가 자동으로 호출되고, 인스턴스가 자동으로 주입.
    // 즉, 해당 변수 및 메서드에 스프링이 관리하는 Bean을 자동으로 매핑해주는 개념.
    @Autowired
    private WishListRepository wishListRepository;

    private WishListEntity create() {
        var wishList = new WishListEntity();
        wishList.setTitle("title");
        wishList.setCategory("category");
        wishList.setAddress("address");
        wishList.setReadAddress("roadAddress");
        wishList.setHomepageLink("");
        wishList.setImageLink("");
        wishList.setVisit(false);
        wishList.setIsVisitCount(0);
        wishList.setLastVisitDate(null);

        return wishList;
    }

    @Test
    public void saveTest(){
        var wishListEnity = create();
        var expected = wishListRepository.save(wishListEnity);

        Assertions.assertNotNull(expected);
        Assertions.assertEquals(1, expected.getIndex());
    }

    @Test
    public void updateTest(){
        var wishListEnity = create();
        var expected = wishListRepository.save(wishListEnity);

        expected.setTitle("update test");
        var saveEntity = wishListRepository.save(expected);

        Assertions.assertEquals("update test", saveEntity.getTitle());
        Assertions.assertEquals(1, wishListRepository.listAll().size());
    }

    @Test
    public void findByIdTest(){
        var wishListEnity = create();
        wishListRepository.save(wishListEnity);

        var expected = wishListRepository.findById(1);

        Assertions.assertEquals(true, expected.isPresent());
        Assertions.assertEquals(1, expected.get().getIndex());
    }

    @Test
    public void deleteTest() {
        var wishListEnity = create();
        wishListRepository.save(wishListEnity);

        wishListRepository.deleteById(1);

        int count = wishListRepository.listAll().size();
        Assertions.assertEquals(0, count);
    }

    @Test
    public void listAllTest() {
        var wishListEnity1 = create();
        wishListRepository.save(wishListEnity1);

        var wishListEnity2 = create();
        wishListRepository.save(wishListEnity2);

        int count = wishListRepository.listAll().size();
        Assertions.assertEquals(2, count);
    }

}