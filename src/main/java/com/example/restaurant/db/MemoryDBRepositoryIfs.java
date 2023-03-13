package com.example.restaurant.db;

import java.util.List;
import java.util.Optional;

// MemoryDBRepositoryIfs - 인터페이스를 통해 다중 상속 지원 (중간 매개 역할)
public interface MemoryDBRepositoryIfs<T> {
    Optional<T> findById(int index);    // findById() : 인덱스에 해당되는 데이터를 리턴하는 메소드
    T save(T entity);                   // save() : 저장하려는 데이터의 인덱스를 확인한 후, 데이터를 저장하는 메소드.
    void deleteById(int index);         // deleteById() : 삭제하려는 데이터의 인덱스를 확인한 후, 해당 데이터를 삭제하는 메소드.
    List<T> listAll();                  // listAll() : 전체 데이터를 리턴시키는 메소드
}
