package com.ssg.sausagecartshareapi.cartshare.dto.response;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShare;
import com.ssg.sausagecartshareapi.cartshare.entity.CartShareItem;
import com.ssg.sausagecartshareapi.cartshare.entity.CartShareMbr;
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
public class CartShareFindResponse {

    @Schema(description = "공유장바구니 id")
    private Long cartShareId;

    @Schema(description = "마스터 여부")
    private boolean mastrYn;

    @Schema(description = "공유장바구니 이름")
    private String cartShareNm;

    @Schema(description = "공유장바구니 상품 개수")
    private int cartShareItemQty;

    @Schema(description = "공유장바구니 멤버 수")
    private int cartShareMbrCnt;

    @Schema(description = "마스터 이름")
    private String mastrNm;

    @Schema(description = "공유장바구니 배송지")
    private String cartShareAddr;

    @Schema(description = "공유장바구니 공통 상품 리스트")
    private CartShareCommonItemInfo commonItemInfo;

    @Schema(description = "공유장바구니 개별 상품 리스트")
    private List<CartSharePersonalItemInfo> personalItemInfo;

    @Schema(description = "공유장바구니 결제 정보")
    private CartShareAmtInfo cartShareAmtInfo;

    public static CartShareFindResponse of(Long myId, List<Long> mbrIdList, CartShare cartShare,
            List<CartShareMbr> cartShareMbrList, List<CartShareItem> cartShareItemList,
            Map<Long, MbrInfo> mbrInfoMap, Map<Long, ItemInfo> itemInfoMap) {
        return CartShareFindResponse.builder()
                .cartShareId(cartShare.getCartShareId())
                .mastrYn(myId.equals(cartShare.getMastrMbrId()))
                .cartShareNm(cartShare.getCartShareNm())
                .cartShareItemQty(
                        cartShareItemList.stream().map(CartShareItem::getItemId).collect(Collectors.toSet()).size())
                .cartShareMbrCnt(cartShareMbrList.size())
                .mastrNm(mbrInfoMap.get(cartShare.getMastrMbrId()).getMbrNm())
                .cartShareAddr(cartShare.getCartShareAddr())
                .commonItemInfo(CartShareCommonItemInfo.of(cartShareItemList, itemInfoMap))
                .personalItemInfo(mbrIdList.stream()
                        .map(mbrId -> CartSharePersonalItemInfo.of(mbrId, cartShare.getMastrMbrId(),
                                cartShareItemList, mbrInfoMap, itemInfoMap))
                        .collect(Collectors.toList()))
                .cartShareAmtInfo(CartShareAmtInfo.of(cartShareItemList, itemInfoMap))
                .build();
    }
}
