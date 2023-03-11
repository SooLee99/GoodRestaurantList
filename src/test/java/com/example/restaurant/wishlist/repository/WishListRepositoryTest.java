package com.example.restaurant.wishlist.repository;

import com.example.restaurant.wishlist.entity.WishListEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListRepositoryTest {

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