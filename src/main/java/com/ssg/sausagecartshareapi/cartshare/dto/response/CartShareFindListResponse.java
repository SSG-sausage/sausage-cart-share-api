package com.ssg.sausagecartshareapi.cartshare.dto.response;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShare;
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
public class CartShareFindListResponse {

    @Schema(description = "공유장바구니 리스트")
    private List<CartShareInfo> cartShareList;

    public static CartShareFindListResponse of(List<CartShare> cartShareList) {
        return CartShareFindListResponse.builder()
                .cartShareList(cartShareList.stream().map(CartShareInfo::of).collect(Collectors.toList()))
                .build();
    }
}
