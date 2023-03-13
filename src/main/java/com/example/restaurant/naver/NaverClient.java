package com.example.restaurant.naver;

import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchImageRes;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

// @Component : 개발자가 직접 작성한 Class를 Spring의 Bean으로 등록할 때, 사용하는 Annotation.
@Component
public class NaverClient {

    // @Value : properties 파일에 세팅한 내용을 Spring 변수에 주입 하는 역할.
    // @Value("${...}") : application.properties 에 정의한 내용을 가져와 사용할 수 있다.
    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverImageSearchUrl;

    // 장소 검색을 요청하는 메소드.
    public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq) {
        // API로 요청 보낼 Uri만들기
        var uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                .queryParams(searchLocalReq.toMultivalueMap())
                .build()
                .encode()
                .toUri();

        // 헤더 세팅
        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 헤더를 담음
        var httpEntity = new HttpEntity<>(headers);

        // 응답받을 타입(res)지정
        var responseType = new ParameterizedTypeReference<SearchLocalRes>(){};

        // api 값 받아와 responseEntity에 저장
        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );
        return responseEntity.getBody();
    }


    // 이미지 검색을 요청하는 메소드.
    public SearchImageRes searchImage(SearchImageReq searchImageReq) {

        // API로 요청 보낼 Uri만들기
        var uri = UriComponentsBuilder.fromUriString(naverImageSearchUrl)
                .queryParams(searchImageReq.toMultivalueMap())
                .build()
                .encode()
                .toUri();

        // 헤더 세팅
        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 헤더를 담음
        var httpEntity = new HttpEntity<>(headers);

        // 응답받을 타입(res)지정
        var responseType = new ParameterizedTypeReference<SearchImageRes>(){};

        // api 값 받아와 responseEntity에 저장
        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );
        return responseEntity.getBody();

    }

}


