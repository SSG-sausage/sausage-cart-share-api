package com.ssg.sausagecartshareapi.cartshare.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProgStatCd {
    IN_PROGRESS("담는 중"),
    DONE("담기 완료");

    private final String description;
}
