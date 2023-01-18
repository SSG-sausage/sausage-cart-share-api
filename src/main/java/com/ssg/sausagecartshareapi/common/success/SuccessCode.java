package com.ssg.sausagecartshareapi.common.success;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    /**
     * 200 OK
     */
    OK_SUCCESS(SuccessStatusCode.OK, "성공입니다."),
    FIND_CART_SHARE_LIST_SUCCESS(SuccessStatusCode.OK, "장바구니 리스트 조회 성공입니다."),
    FIND_CART_SHARE_SUCCESS(SuccessStatusCode.OK, "단일 장바구니 조회 성공입니다."),

    /**
     * 201 CREATED
     */
    CREATED_SUCCESS(SuccessStatusCode.CREATED, "생성 성공입니다."),

    /**
     * 202 ACCEPTED
     */

    /**
     * 204 NO_CONTENT
     */
    ;

    private final SuccessStatusCode statusCode;
    private final String message;

    public int getStatus() {
        return statusCode.getStatus();
    }
}