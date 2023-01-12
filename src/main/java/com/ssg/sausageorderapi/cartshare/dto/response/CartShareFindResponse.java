package com.ssg.sausageorderapi.cartshare.dto.response;

import com.ssg.sausageorderapi.cartshare.entity.CartShare;
import com.ssg.sausageorderapi.cartshare.entity.CartShareItem;
import com.ssg.sausageorderapi.cartshare.entity.CartShareMbr;
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

    private Long cartShareId;
    private Long mastrMbrId;
    private List<Long> mbrIdList;
    private String cartShareNm;
    private String cartShareAddr;
    private List<CartShareItemInfo> commonItemList;
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
