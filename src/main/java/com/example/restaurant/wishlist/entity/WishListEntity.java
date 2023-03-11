package com.example.restaurant.wishlist.entity;


import com.example.restaurant.db.MemoryDBEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WishListEntity extends MemoryDBEntity {
    private String title;                   // 장소명
    private String category;                // 카테고리
    private String address;                 // 주소
    private String readAddress;             // 도로명
    private String homepageLink;            // 홈페이지 주소
    private String imageLink;               // 가게 이미지 주소
    private boolean isVisit;            // 방문여부
    private int isVisitCount;            // 방문 횟수
    private LocalDateTime lastVisitDate;    // 마지막 방문일자

}
