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

    // 테스트 수행 동작 시, 객체를 생성하는 메소드.
    private WishListEntity create() {
        var wishList = new WishListEntity();
        wishList.setTitle("title");
        wishList.setCategory("category");
        wishList.setAddress("address");
        wishList.setRoadAddress("roadAddress");
        wishList.setHomePageLink("");
        wishList.setImageLink("");
        wishList.setVisit(false);
        wishList.setVisitCount(0);
        wishList.setLastVisitDate(null);

        return wishList;
    }

    // saveTest() : 데이터가 재대로 저장되는지 확인하는 메소드.
    @Test
    public void saveTest(){
        var wishListEnity = create();
        // 데이터 1개를 저장함. -> (총 데이터의 개수 : 1개)
        var expected = wishListRepository.save(wishListEnity);

        // Assertions : 인수를 검증하고 조건에 맞지 않는 경우 IllegalArgumentException 또는 IllegalStateException를 발생시킴.
        // Assertions : 테스트가 원하는 결과를 제대로 리턴하는지 에러는 발생하지 않는지 확인할 때 사용하는 메소드.

        // 데이터를 리턴했을 때, 데이터의 개수가 null이면 안된다. -> (총 데이터의 개수 : 1개)
        Assertions.assertNotNull(expected);
        // 첫 번째 인덱스의 데이터가 존재하면, 데이터 저장 성공.
        Assertions.assertEquals(1, expected.getIndex());
    }

    // updateTest() : 데이터 수정 시, 수정이 재대로 되는지 확인하는 메소드.
    @Test
    public void updateTest(){
        var wishListEnity = create();
        // 데이터 1개를 저장함. -> (총 데이터의 개수 : 1개)
        var expected = wishListRepository.save(wishListEnity);

        // 객체 제목을 "title"에서 "update test"로 변경해서 저장함.
        expected.setTitle("update test");
        var saveEntity = wishListRepository.save(expected);

        // 변경된 데이터가 재대로 저장되어있는지 확인.
        Assertions.assertEquals("update test", saveEntity.getTitle());

        // 데이터를 수정했기 때문에 데이터는 총 1개만 존재해야 한다. 확인되면 테스트 성공.
        Assertions.assertEquals(1, wishListRepository.findAll().size());
    }

    // findByIdTest() : 저장시킨 데이터가 재대로 리턴되는지 확인하는 메소드.
    @Test
    public void findByIdTest(){
        var wishListEnity = create();
        // 데이터 1개를 저장함. -> (총 데이터의 개수 : 1개)
        wishListRepository.save(wishListEnity);

        // 데이터 1개를 리턴시킴. -> (총 데이터의 개수 : 1개)
        var expected = wishListRepository.findById(1);

        // 객체 안의 값이 존재해야 함.
        Assertions.assertEquals(true, expected.isPresent());
        // 인덱스의 값을 리턴했을 때, 이 인덱스는 1이어야 함.
        Assertions.assertEquals(1, expected.get().getIndex());
    }

    // deleteTest() : 저장시킨 데이터가 재대로 삭제되는지 확인하는 메소드.
    @Test
    public void deleteTest() {
        var wishListEnity = create();
        // 데이터 1개를 저장함. -> (총 데이터의 개수 : 1개)
        wishListRepository.save(wishListEnity);

        // 데이터 1개를 삭제함. -> (총 데이터의 개수 : 0개)
        wishListRepository.deleteById(1);

        // 총 데이터의 개수가 0개이면, 삭제 성공.
        int count = wishListRepository.findAll().size();
        Assertions.assertEquals(0, count);
    }

    // listAllTest() : 전체 리스트가 재대로 리턴되는지 확인하는 메소드.
    @Test
    public void listAllTest() {
        // 객체1 생성.
        var wishListEnity1 = create();
        wishListRepository.save(wishListEnity1);

        // 객체2 생성.
        var wishListEnity2 = create();
        wishListRepository.save(wishListEnity2);

        // 객체 2개가 리턴되면 테스트 성공.
        int count = wishListRepository.findAll().size();
        Assertions.assertEquals(2, count);
    }

}