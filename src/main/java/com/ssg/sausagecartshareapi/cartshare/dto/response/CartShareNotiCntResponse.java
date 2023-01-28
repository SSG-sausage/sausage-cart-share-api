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
public class CartShareNotiCntResponse {

    @Schema(description = "신규 알림 개수")
    private int cnt;

    public static CartShareNotiCntResponse of(int cnt) {
        return CartShareNotiCntResponse.builder()
                .cnt(cnt)
                .build();
    }
}
