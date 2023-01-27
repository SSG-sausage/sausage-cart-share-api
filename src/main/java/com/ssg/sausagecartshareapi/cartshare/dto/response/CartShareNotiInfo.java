package com.ssg.sausagecartshareapi.cartshare.dto.response;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShareNoti;
import com.ssg.sausagecartshareapi.cartshare.entity.NotiCd;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
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
public class CartShareNotiInfo {

    @Schema(description = "공유장바구니알림 id")
    private Long cartShareNotiId;

    @Schema(description = "알림타입코드")
    private NotiCd notiCd;

    @Schema(description = "알림내용")
    private String cartShareNotiCntt;

    @Schema(description = "읽음여부")
    private boolean readYn;

    @Schema(description = "생성일시")
    private LocalDateTime regDts;

    public static CartShareNotiInfo of(CartShareNoti cartShareNoti) {
        return CartShareNotiInfo.builder()
                .cartShareNotiId(cartShareNoti.getCartShareNotiId())
                .notiCd(cartShareNoti.getNotiCd())
                .cartShareNotiCntt(cartShareNoti.getCartShareNotiCntt())
                .readYn(cartShareNoti.getReadYn())
                .regDts(cartShareNoti.getRegDts())
                .build();
    }
}
