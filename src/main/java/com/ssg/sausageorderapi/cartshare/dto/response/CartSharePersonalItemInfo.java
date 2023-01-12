package com.ssg.sausageorderapi.cartshare.dto.response;

import com.ssg.sausageorderapi.cartshare.entity.CartShareItem;
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
public class CartSharePersonalItemInfo {

    private Long mbrId;
    private List<CartShareItemInfo> cartShareItemList;

    public static CartSharePersonalItemInfo of(Long mbrId, List<CartShareItem> cartShareItemList) {
        return CartSharePersonalItemInfo.builder()
                .mbrId(mbrId)
                .cartShareItemList(
                        cartShareItemList.stream().filter(cartShareItem ->
                                        !cartShareItem.getCommYn() && cartShareItem.getMbrId().equals(mbrId))
                                .map(CartShareItemInfo::of)
                                .collect(Collectors.toList()))
                .build();
    }
}
