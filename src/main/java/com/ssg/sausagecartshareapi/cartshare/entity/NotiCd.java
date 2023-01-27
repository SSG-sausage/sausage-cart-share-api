package com.ssg.sausagecartshareapi.cartshare.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum NotiCd {

    CART_SHARE_CAL("쓱총무"),
    CART_SHARE("함께 장보기"),
    CART_SHARE_ORD("주문 안내");

    private final String description;
}
