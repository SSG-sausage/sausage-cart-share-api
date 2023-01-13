package com.ssg.sausageorderapi.cartshare.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class CartShareUpdateDto {

    private Long cartShareId;
    private Long mbrId;
    private String message;

    public static CartShareUpdateDto of(Long cartShareId, Long mbrId, String message) {
        return CartShareUpdateDto.builder()
                .cartShareId(cartShareId)
                .mbrId(mbrId)
                .message(message)
                .build();
    }
}
