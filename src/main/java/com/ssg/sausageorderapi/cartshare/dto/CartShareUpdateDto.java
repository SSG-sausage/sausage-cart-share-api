package com.ssg.sausageorderapi.cartshare.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartShareUpdateDto {

    private Long cartShareId;
    private Long mbrId;
    private String message;
}
