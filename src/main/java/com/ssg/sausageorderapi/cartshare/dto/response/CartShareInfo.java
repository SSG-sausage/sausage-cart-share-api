package com.ssg.sausageorderapi.cartshare.dto.response;

import com.ssg.sausageorderapi.cartshare.entity.CartShare;
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
public class CartShareInfo {

    private Long cartShareId;
    private Long mastrMbrId;
    private String cartShareNm;

    public static CartShareInfo of(CartShare cartShare) {
        return CartShareInfo.builder()
                .cartShareId(cartShare.getCartShareId())
                .mastrMbrId(cartShare.getMastrMbrId())
                .cartShareNm(cartShare.getCartShareNm())
                .build();
    }
}
