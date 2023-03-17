package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
// SearchImageRes 클래스 : Naver Image API에서 결과 정보를 받기 위한 객체 클래스.
public class SearchImageRes {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<SearchImageItem> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchImageItem {
        private String title;
        private String link;
        private String thumbnail;
        private String sizeheight;
        private String sizewidth;
    }
}