package com.ssg.sausagecartshareapi.cartshare.dto.response;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShare;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "공유장바구니 id")
    private Long cartShareId;

    @Schema(description = "마스터 멤버 id")
    private Long mastrMbrId;

    @Schema(description = "공유장바구니 이름")
    private String cartShareNm;

    public static CartShareInfo of(CartShare cartShare) {
        return CartShareInfo.builder()
                .cartShareId(cartShare.getCartShareId())
                .mastrMbrId(cartShare.getMastrMbrId())
                .cartShareNm(cartShare.getCartShareNm())
                .build();
    }
}
