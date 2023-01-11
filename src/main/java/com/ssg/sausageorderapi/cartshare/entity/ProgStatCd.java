package com.ssg.sausageorderapi.cartshare.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProgStatCd {

    NONE("없음"),
    IN_PROGRESS("담는 중"),
    DONE("담기 완료");

    private final String description;
}
