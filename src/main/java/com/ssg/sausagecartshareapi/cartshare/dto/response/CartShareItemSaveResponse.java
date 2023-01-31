package com.ssg.sausagecartshareapi.cartshare.dto.response;

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
public class CartShareItemSaveResponse {

    @Schema(description = "신규 상품 여부")
    private boolean newItemYn;

    @Schema(description = "상품 개수")
    private int itemQty;

    public static CartShareItemSaveResponse of(boolean newItemYn, int itemQty) {
        return CartShareItemSaveResponse.builder()
                .newItemYn(newItemYn)
                .itemQty(itemQty)
                .build();
    }
}
