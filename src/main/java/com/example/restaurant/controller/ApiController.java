package com.example.restaurant.controller;

import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @Slf4j : 다양한 로깅 프레임 워크에 대한 [추상화(인터페이스) 역할을 하는] 라이브러리.
@Slf4j
// @RestController :  Json 형태로 객체 데이터를 반환하는 어노테이션.
@RestController
@RequestMapping("/api/restaurant")
// @RequiredArgsConstructor : final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션.
@RequiredArgsConstructor

// ApiController 클래스 : api를 통해 검색한 데이터를 컨트롤러로 제공해주는 클래스.
public class ApiController {

    private final WishListService wishListService;

    // 검색 컨트롤러
    @GetMapping("/search")
    public WishListDto search(@RequestParam String query){
        return wishListService.search(query);
    }

    // 데이터를 저장시키는 컨트롤러
    @PostMapping("")
    public WishListDto add(@RequestBody WishListDto wishListDto){
        log.info("{}", wishListDto);
        return wishListService.add(wishListDto);
    }

    @GetMapping("/all")
    public List<WishListDto> findAll(){
        return wishListService.findAll();
    }

    @DeleteMapping("/{index}")
    public void delete(@PathVariable int index){
        wishListService.delete(index);
    }

    @PostMapping("/{index}")
    public void addVisit(@PathVariable int index){
        wishListService.addVisit(index);
    }

}