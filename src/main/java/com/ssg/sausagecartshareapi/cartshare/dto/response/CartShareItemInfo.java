package com.ssg.sausagecartshareapi.cartshare.dto.response;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShareItem;
import com.ssg.sausagecartshareapi.common.client.internal.dto.response.ItemListInfoResponse.ItemInfo;
import com.ssg.sausagecartshareapi.common.util.PriceUtils;
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

    @Schema(description = "상품 브랜드 이름")
    private String itemBrandNm;

    @Schema(description = "상품 이름")
    private String itemNm;

    @Schema(description = "상품 가격")
    private String itemAmt;

    @Schema(description = "상품 수량")
    private String itemImgUrl;

    @Schema(description = "배송타입코드")
    private String shppCd;

    @Schema(description = "상품 수량")
    private int itemQty;

    public static CartShareItemInfo of(CartShareItem cartShareItem, ItemInfo itemInfo) {
        return CartShareItemInfo.builder()
                .cartShareItemId(cartShareItem.getCartShareItemId())
                .itemId(itemInfo.getItemId())
                .itemBrandNm(itemInfo.getItemBrandNm())
                .itemNm(itemInfo.getItemNm())
                .itemAmt(PriceUtils.toString(itemInfo.getItemAmt()))
                .itemImgUrl(itemInfo.getItemImgUrl())
                .shppCd(itemInfo.getShppCd())
                .itemQty(cartShareItem.getItemQty())
                .build();
    }
}
