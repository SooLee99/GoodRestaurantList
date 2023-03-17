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

@Component
// NaverClient 클래스 : application.yaml 파일에 저장되어 있는 정보를 객체에 저장하고, Naver API에 정보 요청을 하는 클래스.
public class NaverClient {

    // (1) application.yaml 파일에서 값을 객체로 가져옴.
    // @Value : 표현식 기반으로 값을 주입해주는 애노테이션.
    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverImageSearchUrl;

    // (2) Naver Api를 사용하여, 지역을 검색하는 메소드.
    public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq){
        // - UriComponents 클래스 : URI를 동적으로 생성해주는 클래스
        // - UriComponents 클래스의 생성자는 모두 package-private 또는 private 이기 때문에,
        //   개발자가 이를 직접 구현하지 않는 이상 생성자를 통해 직접 생성할 수는 없다.

        // - UriComponentsBuilder 클래스 : UriComponents 를 Build 할 수 있도록 도와 주는 클래스.
        // fromUriString() : URI 템플릿이 있는 정적 팩토리 메서드.
        var uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                // queryParams() : URI 구성 요소를 추가하거나 교체합니다.
                // searchLocalReq.toMultiValueMap() : 데이터를 Map형식으로 변경합니다.
                .queryParams(searchLocalReq.toMultiValueMap())

                // build() : UriComponents를 빌드합니다.
                .build()

                // encode() : URI 템플릿 및 URI 변수를 인코딩하도록 요청합니다.
                .encode()

                // toUri() : 변수를 확장하고 URI를 가져옵니다.
                .toUri();

        // Naver API 호출 요청 예시를 참고하여 헤더 제작.
        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 위 헤더를 httpEntity에 담아준다.
        var httpEntity = new HttpEntity<>(headers);

        // 지역 검색을 하고 싶은 정보를 객체에 저장함.
        var responseType = new ParameterizedTypeReference<SearchLocalRes>(){};

        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();
    }

    // (3) Naver Api를 사용하여, 이미지를 검색하는 메소드. (위와 동일.)
    public SearchImageRes searchImage(SearchImageReq searchImageReq){
        var uri = UriComponentsBuilder.fromUriString(naverImageSearchUrl)
                .queryParams(searchImageReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<SearchImageRes>(){};


        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();
    }
}