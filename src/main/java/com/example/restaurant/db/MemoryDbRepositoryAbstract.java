package com.example.restaurant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// MemoryDBRepositoryIfs 인터페이스를 상속해줌.
// MemoryDbRepositoryAbstract 추상클래스 : "메인화면"에서 제공하는 기능을 수행하는 클래스.
abstract public class MemoryDbRepositoryAbstract<T extends MemoryDBEntity> implements MemoryDBRepositoryIfs<T>{

    private final List<T> db = new ArrayList<>();    // 데이터(인덱스)를 저장하는 리스트.
    private int index = 0;

    // findById() : 인덱스에 해당되는 데이터를 리턴하는 메소드.
    @Override
    public Optional<T> findById(int index) {
        // findFirst() : filter 조건에 일치하는 element 1개를 Optional 로 리턴한다. 조건에 일치하는 요소가 없다면 empty 가 리턴됩니다.
        db.stream().filter(it -> it.getIndex() == index).findFirst();
        return Optional.empty();
    }

    // save() : 저장하려는 데이터의 인덱스를 확인한 후, 데이터를 저장하는 메소드.
    @Override
    public T save(T entity) {
        var optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        if(optionalEntity.isEmpty()) {
            // db에 데이터가 없는 경우
            index++;
            entity.setIndex(index);
            db.add(entity);
            return entity;

        }else{
            // db에 데이터가 있는 경우
            var preIndex = optionalEntity.get().getIndex(); // 이전에 가지고 있는 인덱스를 가져온다.
            entity.setIndex(preIndex);                      // 객체 안에 새로운 인덱스를 설정해준다.
            deleteById(preIndex);                           // 이전에 있던 인덱스를 지운다.
            db.add(entity);
            return entity;
        }
    }

    // deleteById() : 삭제하려는 데이터의 인덱스를 확인한 후, 해당 데이터를 삭제하는 메소드.
    @Override
    public void deleteById(int index) {
        var optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst();

        // isPresent() : Optional 객체가 값을 가지고 있다면 true, 값이 없다면 false 리턴.
        if(optionalEntity.isPresent()){
            db.remove(optionalEntity.get());    // 해당 객체의 값을 삭제시킨다.
        }
    }

    // listAll() : 전체 데이터를 리턴시키는 메소드
    @Override
    public List<T> findAll() {
        return db;
    }
}
