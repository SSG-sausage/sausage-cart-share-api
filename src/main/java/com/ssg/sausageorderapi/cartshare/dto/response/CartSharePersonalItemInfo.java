package com.ssg.sausageorderapi.cartshare.dto.response;

import com.ssg.sausageorderapi.cartshare.entity.CartShareItem;
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
public class CartSharePersonalItemInfo {

    @Schema(description = "멤버 id")
    private Long mbrId;

    @Schema(description = "공유장바구니 상품 리스트")
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
