package com.ssg.sausagecartshareapi.cartshare.dto.response;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShareItem;
import com.ssg.sausagecartshareapi.common.client.internal.dto.response.ItemListInfoResponse.ItemInfo;
import com.ssg.sausagecartshareapi.common.util.PriceUtils;
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
public class CartShareCommonItemInfo {

    @Schema(description = "공동 상품 총액")
    private String commonAmt;

    @Schema(description = "공유장바구니 상품 리스트")
    private List<CartShareItemInfo> cartShareItemList;

    public static CartShareCommonItemInfo of(List<CartShareItem> cartShareItemList, Map<Long, ItemInfo> itemInfoMap) {
        List<CartShareItemInfo> cartShareItemInfoList = cartShareItemList.stream()
                .filter(CartShareItem::getCommYn)
                .map(cartShareItem -> CartShareItemInfo.of(
                        cartShareItem, itemInfoMap.get(cartShareItem.getItemId())))
                .collect(Collectors.toList());
        return CartShareCommonItemInfo.builder()
                .commonAmt(PriceUtils.toString(calculateCommonAmt(cartShareItemInfoList)))
                .cartShareItemList(cartShareItemInfoList)
                .build();
    }

    private static int calculateCommonAmt(List<CartShareItemInfo> cartShareItemInfoList) {
        return cartShareItemInfoList.stream()
                .mapToInt(cartShareItemInfo ->
                        PriceUtils.toInt(cartShareItemInfo.getItemAmt()) * cartShareItemInfo.getItemQty()).sum();
    }
}
