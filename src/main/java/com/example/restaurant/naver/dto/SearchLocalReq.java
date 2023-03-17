package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data   // @Getter / @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor 합쳐놓은 어노테이션.
@NoArgsConstructor  // 기본 생성자를 생성
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만듦

// Naver Local API 요청 시, 필요한 파라미터를 작성하는 클래스.
public class SearchLocalReq {

    private String query = "";

    private int display = 1;

    private int start = 1;

    private String sort = "random";

    public MultiValueMap<String, String> toMultiValueMap(){
        var map = new LinkedMultiValueMap<String, String>();

        map.add("query",query);
        map.add("display",String.valueOf(display));
        map.add("start", String.valueOf(start));
        map.add("sort",sort);
        return map;
    }
}