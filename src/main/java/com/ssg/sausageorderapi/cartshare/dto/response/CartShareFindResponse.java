package com.ssg.sausageorderapi.cartshare.dto.response;

import com.ssg.sausageorderapi.cartshare.entity.CartShare;
import com.ssg.sausageorderapi.cartshare.entity.CartShareItem;
import com.ssg.sausageorderapi.cartshare.entity.CartShareMbr;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
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

    @Schema(description = "마스터 멤버 id")
    private Long mastrMbrId;

    @Schema(description = "공유장바구니 멤버 id 리스트")
    private List<Long> mbrIdList;

    @Schema(description = "공유장바구니 이름")
    private String cartShareNm;

    @Schema(description = "공유장바구니 배송지")
    private String cartShareAddr;

    @Schema(description = "공유장바구니 공통 상품 리스트")
    private List<CartShareItemInfo> commonItemList;

    @Schema(description = "공유장바구니 개별 상품 리스트")
    private List<CartSharePersonalItemInfo> personalItemList;

    // TODO: 2023/01/11 장바구니 UI에 맞춰서 response 수정
    public static CartShareFindResponse of(CartShare cartShare, List<CartShareMbr> cartShareMbrList,
            List<CartShareItem> cartShareItemList) {
        List<Long> mbrIdList = cartShareMbrList.stream().map(CartShareMbr::getMbrId).collect(Collectors.toList());
        return CartShareFindResponse.builder()
                .cartShareId(cartShare.getCartShareId())
                .mastrMbrId(cartShare.getMastrMbrId())
                .mbrIdList(mbrIdList)
                .cartShareNm(cartShare.getCartShareNm())
                .cartShareAddr(cartShare.getCartShareAddr())
                .commonItemList(cartShareItemList.stream().filter(CartShareItem::getCommYn).map(CartShareItemInfo::of)
                        .collect(Collectors.toList()))
                .personalItemList(mbrIdList.stream()
                        .map(mbrId -> CartSharePersonalItemInfo.of(mbrId, cartShareItemList))
                        .collect(Collectors.toList()))
                .build();
    }
}
