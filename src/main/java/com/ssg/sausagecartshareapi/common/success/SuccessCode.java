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
    SAVE_CART_SHARE_ITEM_SUCCESS(SuccessStatusCode.OK, "장바구니 상품 추가 성공입니다."),
    FIND_CART_SHARE_LIST_SUCCESS(SuccessStatusCode.OK, "장바구니 리스트 조회 성공입니다."),
    FIND_CART_SHARE_SUCCESS(SuccessStatusCode.OK, "단일 장바구니 조회 성공입니다."),
    FIND_CART_SHARE_ITEM_LIST_SUCCESS(SuccessStatusCode.OK, "장바구니 상품 리스트 조회 성공입니다."),
    FIND_CART_SHARE_MBR_LIST_SUCCESS(SuccessStatusCode.OK, "장바구니 멤버 리스트 조회 성공입니다."),
    FIND_CART_SHARE_NOTI_LIST_SUCCESS(SuccessStatusCode.OK, "장바구니 알림 리스트 조회 성공입니다."),
    FIND_CART_SHARE_NOTI_CNT_SUCCESS(SuccessStatusCode.OK, "장바구니 신규 알림 개수 조회 성공입니다."),
    VALIDATE_CART_SHARE_MBR_SUCCESS(SuccessStatusCode.OK, "장바구니 멤버 권한 체크 성공입니다."),
    VALIDATE_CART_SHARE_MASTR_SUCCESS(SuccessStatusCode.OK, "장바구니 마스터 권한 체크 성공입니다."),

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
