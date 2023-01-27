package com.ssg.sausagecartshareapi.cartshare.dto.response;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShareNoti;
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
public class CartShareNotiFindListResponse {

    @Schema(description = "공유장바구니알림 리스트")
    private List<CartShareNotiInfo> cartShareNotiList;

    public static CartShareNotiFindListResponse of(List<CartShareNoti> cartShareNotiList) {
        return CartShareNotiFindListResponse.builder()
                .cartShareNotiList(cartShareNotiList.stream()
                        .map(CartShareNotiInfo::of)
                        .collect(Collectors.toList()))
                .build();
    }
}
