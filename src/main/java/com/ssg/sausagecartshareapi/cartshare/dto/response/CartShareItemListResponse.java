package com.ssg.sausagecartshareapi.cartshare.dto.response;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShareItem;
import com.ssg.sausagecartshareapi.common.client.internal.dto.response.ItemListInfoResponse.ItemInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
public class CartShareItemListResponse {

    @Schema(description = "공유장바구니상품 리스트")
    private List<CartShareItemInfo> cartShareItemList;

    @ToString
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder(access = AccessLevel.PRIVATE)
    private static class CartShareItemInfo {

        @Schema(description = "공유장바구니상품 ID")
        private Long cartShareItemId;

        @Schema(description = "상품 ID")
        private Long itemId;

        @Schema(description = "멤버 ID")
        private Long mbrId;

        @Schema(description = "상품이름")
        private String itemNm;

        @Schema(description = "상품가격")
        private int itemAmt;

        @Schema(description = "배송타입코드")
        private String shppCd;

        @Schema(description = "상품수량")
        private int itemQty;

        @Schema(description = "공통상품여부")
        private boolean comYn;

        private static CartShareItemInfo of(CartShareItem cartShareItem, ItemInfo itemInfo) {
            return CartShareItemInfo.builder()
                    .cartShareItemId(cartShareItem.getCartShareItemId())
                    .itemId(cartShareItem.getItemId())
                    .mbrId(cartShareItem.getMbrId())
                    .itemNm(itemInfo.getItemNm())
                    .itemAmt(itemInfo.getItemAmt())
                    .shppCd(itemInfo.getShppCd())
                    .itemQty(cartShareItem.getItemQty())
                    .comYn(cartShareItem.getCommYn())
                    .build();
        }
    }

    public static CartShareItemListResponse of(List<CartShareItem> cartShareItemList, Map<Long, ItemInfo> itemInfoMap) {
        return CartShareItemListResponse.builder()
                .cartShareItemList(cartShareItemList.stream()
                        .map(item -> CartShareItemInfo.of(item, itemInfoMap.get(item.getItemId())))
                        .collect(Collectors.toList()))
                .build();
    }
}
