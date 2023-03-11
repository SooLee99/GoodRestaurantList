package com.example.restaurant.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDBRepositoryIfs<T> {
    Optional<T> findById(int index);    // 해당 데이터를 찾아서 리턴하는 메소드
    T save(T entity);                   // 저장하는 메소드
    void deleteById(int index);         // 삭제하는 메소드
    List<T> listAll();                  // 전체를 리턴시키는 메소드
}
