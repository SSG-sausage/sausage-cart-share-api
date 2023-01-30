package com.ssg.sausagecartshareapi.cartshare.dto.response;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShareItem;
import com.ssg.sausagecartshareapi.cartshare.entity.CartShareMbr;
import com.ssg.sausagecartshareapi.cartshare.entity.ProgStatCd;
import com.ssg.sausagecartshareapi.common.client.internal.dto.response.ItemListInfoResponse.ItemInfo;
import com.ssg.sausagecartshareapi.common.client.internal.dto.response.MbrListInfoResponse.MbrInfo;
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

    @Schema(description = "편집 가능 여부")
    private boolean editYn;

    @Schema(description = "진행 상태")
    private ProgStatCd progStatCd;

    @Schema(description = "개별 상품 총액")
    private int personalAmt;

    @Schema(description = "공유장바구니 상품 리스트")
    private List<CartShareItemInfo> cartShareItemList;

    public static CartSharePersonalItemInfo of(Long myId, CartShareMbr cartShareMbr, Long mastrId,
            List<CartShareItem> cartShareItemList,
            Map<Long, MbrInfo> mbrInfoMap, Map<Long, ItemInfo> itemInfoMap) {
        List<CartShareItemInfo> cartShareItemInfoList = toNotCommPersonalCartShareItemInfoList(cartShareItemList,
                cartShareMbr, itemInfoMap);
        return CartSharePersonalItemInfo.builder()
                .mbrNm(mbrInfoMap.get(cartShareMbr.getMbrId()).getMbrNm())
                .mastrYn(cartShareMbr.getMbrId().equals(mastrId))
                .editYn(myId.equals(cartShareMbr.getMbrId()))
                .progStatCd(cartShareMbr.getProgStatCd())
                .personalAmt(calculatePersonalAmt(cartShareItemInfoList))
                .cartShareItemList(cartShareItemInfoList)
                .build();
    }

    private static List<CartShareItemInfo> toNotCommPersonalCartShareItemInfoList(List<CartShareItem> cartShareItemList,
            CartShareMbr cartShareMbr, Map<Long, ItemInfo> itemInfoMap) {
        return cartShareItemList.stream().filter(cartShareItem ->
                        !cartShareItem.getCommYn() && cartShareItem.getMbrId().equals(cartShareMbr.getMbrId()))
                .map(cartShareItem -> CartShareItemInfo.of(
                        cartShareItem, itemInfoMap.get(cartShareItem.getItemId())))
                .collect(Collectors.toList());
    }

    private static int calculatePersonalAmt(List<CartShareItemInfo> cartShareItemInfoList) {
        return cartShareItemInfoList.stream()
                .mapToInt(cartShareItemInfo -> cartShareItemInfo.getItemAmt() * cartShareItemInfo.getItemQty()).sum();
    }
}
