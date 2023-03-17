package com.example.restaurant.wishlist.service;

import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.entity.WishListEntity;
import com.example.restaurant.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// @Service : Dao가 DB에서 받아온 데이터를 전달받아 가공.
// @RequiredArgsConstructor : final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션.
@Service
@RequiredArgsConstructor
// WishListService 클래스 : Naver API에서 제공받은 데이터를 화면에 제공해주는 클래스.
public class WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

    // search() : 컨트롤러를 통해서 검색하고, 가져온 정보를 메인화면에 반환해주는 메소드.
    public WishListDto search(String query){
        // (1) 지역 검색
        // SearchLocalReq() : Naver Local API 검색 요청 시, 필요한 파라미터를 작성하는 클래스.
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);                                 // 검색할 메시지 작성.

        var searchLocalRes = naverClient.searchLocal(searchLocalReq);   // 검색 완료 후, 결과를 저장하는 객체.

        // 만약 지역을 검색한 결과가 1개 이상 존재하는 경우.
        if(searchLocalRes.getTotal() > 0){
            // localItem : 첫 번째 아이템 저장.
            var localItem = searchLocalRes.getItems().stream().findFirst().get();
            // 첫 번째 아이템의 이미지를 저장하는 쿼리문. ("<[^>]*>" -> 이상한 문자열 다 제거.)
            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>","");
            // SearchImageReq() : Naver Image API 요청 파라미터를 작성하는 클래스.
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            // (2) 이미지 검색
            var searchImageRes = naverClient.searchImage(searchImageReq);

            // 검색된 이미지가 1개 이상 존재하는 경우.
            if(searchImageRes.getTotal() > 0){
                var imageItem = searchImageRes.getItems().stream().findFirst().get();

                // (3) 결과를 반환
                var result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());
                return result;
            }
        }

        // 검색한 결과가 존재하지 않는 경우. -> 빈 객체의 값을 반환함.
        return new WishListDto();
    }

    // add() : wishListDto 객체 + entity 객체 저장하는 메소드.
    public WishListDto add(WishListDto wishListDto) {
        var entity = dtoToEntity(wishListDto);
        var saveEntity = wishListRepository.save(entity);
        return entityToDto(saveEntity);
    }

    // dtoToEntity() : "메인 화면"에서 사용할 객체에 데이터를 저장시키는 메소드.
    private WishListEntity dtoToEntity(WishListDto wishListDto){
        var entity = new WishListEntity();
        entity.setIndex(wishListDto.getIndex());
        entity.setTitle(wishListDto.getTitle());
        entity.setCategory(wishListDto.getCategory());
        entity.setAddress(wishListDto.getAddress());
        entity.setRoadAddress(wishListDto.getRoadAddress());
        entity.setHomePageLink(wishListDto.getHomePageLink());
        entity.setImageLink(wishListDto.getImageLink());
        entity.setVisit(wishListDto.isVisit());
        entity.setVisitCount(wishListDto.getVisitCount());
        entity.setLastVisitDate(wishListDto.getLastVisitDate());
        return entity;
    }

    // entityToDto() : "메인 화면"에서 사용할 객체에 데이터를 저장시키는 메소드.
    private WishListDto entityToDto(WishListEntity wishListEntity){
        var dto = new WishListDto();
        dto.setIndex(wishListEntity.getIndex());
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getCategory());
        dto.setAddress(wishListEntity.getAddress());
        dto.setRoadAddress(wishListEntity.getRoadAddress());
        dto.setHomePageLink(wishListEntity.getHomePageLink());
        dto.setImageLink(wishListEntity.getImageLink());
        dto.setVisit(wishListEntity.isVisit());
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVisitDate(wishListEntity.getLastVisitDate());
        return dto;
    }

    public List<WishListDto> findAll() {
        return wishListRepository.findAll()
                .stream()
                .map(it -> entityToDto(it))
                .collect(Collectors.toList());
    }

    public void delete(int index) {
        wishListRepository.deleteById(index);
    }

    public void addVisit(int index){
        var wishItem = wishListRepository.findById(index);
        if(wishItem.isPresent()){
            var item = wishItem.get();
            item.setVisit(true);
            item.setVisitCount(item.getVisitCount()+1);
        }
    }
}