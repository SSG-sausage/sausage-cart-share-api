package com.ssg.sausageorderapi.cartshare.dto.response;

import com.ssg.sausageorderapi.cartshare.entity.CartShareItem;
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
public class CartShareItemInfo {

    @Schema(description = "공유장바구니상품 id")
    private Long cartShareItemId;

    @Schema(description = "상품 id")
    private Long itemId;

    @Schema(description = "상품 수량")
    private int itemQty;

    // TODO: 2023/01/12 아이템 정보 조회해서 수정하기 
    public static CartShareItemInfo of(CartShareItem cartShareItem) {
        return CartShareItemInfo.builder()
                .cartShareItemId(cartShareItem.getCartShareItemId())
                .itemId(cartShareItem.getItemId())
                .itemQty(cartShareItem.getItemQty())
                .build();
    }
}
