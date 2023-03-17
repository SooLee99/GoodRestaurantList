package com.example.restaurant.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data : @Getter, @Setter, @ToString, @EqualsAndHashCode 와 @RequiredArgsConstructor 를 합쳐놓은 어노테이션.
// @NoArgsConstructor : 파라미터가 없는 기본 생성자를 생성해주는 어노테이션.
// @AllArgsConstructor : 모든 필드 값을 파라미터로 받는 생성자를 만들어주는 어노테이션.

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoryDBEntity {
    private Integer index;
}
