package com.ssg.sausagecartshareapi.cartshare.dto.response;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShareItem;
import com.ssg.sausagecartshareapi.common.client.internal.dto.response.ItemListInfoResponse.ItemInfo;
import com.ssg.sausagecartshareapi.common.client.internal.dto.response.MbrListInfoResponse.MbrInfo;
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
public class CartSharePersonalItemInfo {

    @Schema(description = "멤버 이름")
    private String mbrNm;

    @Schema(description = "마스터 여부")
    private boolean mastrYn;

    @Schema(description = "개별 상품 총액")
    private String personalAmt;

    @Schema(description = "공유장바구니 상품 리스트")
    private List<CartShareItemInfo> cartShareItemList;

    public static CartSharePersonalItemInfo of(Long mbrId, Long mastrId, List<CartShareItem> cartShareItemList,
            Map<Long, MbrInfo> mbrInfoMap, Map<Long, ItemInfo> itemInfoMap) {
        List<CartShareItemInfo> cartShareItemInfoList = cartShareItemList.stream().filter(cartShareItem ->
                        !cartShareItem.getCommYn() && cartShareItem.getMbrId().equals(mbrId))
                .map(cartShareItem -> CartShareItemInfo.of(
                        cartShareItem, itemInfoMap.get(cartShareItem.getItemId())))
                .collect(Collectors.toList());
        return CartSharePersonalItemInfo.builder()
                .mbrNm(mbrInfoMap.get(mbrId).getMbrNm())
                .mastrYn(mbrId.equals(mastrId))
                .personalAmt(PriceUtils.toString(calculatePersonalAmt(cartShareItemInfoList)))
                .cartShareItemList(cartShareItemInfoList)
                .build();
    }

    private static int calculatePersonalAmt(List<CartShareItemInfo> cartShareItemInfoList) {
        return cartShareItemInfoList.stream()
                .mapToInt(cartShareItemInfo ->
                        PriceUtils.toInt(cartShareItemInfo.getItemAmt()) * cartShareItemInfo.getItemQty()).sum();
    }
}
