package com.ssg.sausagecartshareapi.cartshare.dto.response;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShare;
import com.ssg.sausagecartshareapi.cartshare.entity.CartShareMbr;
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
public class CartShareMbrIdListResponse {

    @Schema(description = "마스터 ID")
    private Long mastrMbrId;

    @Schema(description = "마스터 이름")
    private String mastrMbrNm;

    @Schema(description = "멤버 ID 리스트")
    private List<Long> mbrIdList;

    @Schema(description = "공유장바구니 이름")
    private String cartShareNm;

    public static CartShareMbrIdListResponse of(CartShare cartShare, List<CartShareMbr> cartShareMbrList,
            Map<Long, MbrInfo> mbrInfoMap) {
        return CartShareMbrIdListResponse.builder()
                .mastrMbrId(cartShare.getMastrMbrId())
                .mastrMbrNm(mbrInfoMap.get(cartShare.getMastrMbrId()).getMbrNm())
                .mbrIdList(cartShareMbrList.stream()
                        .map(CartShareMbr::getMbrId)
                        .collect(Collectors.toList()))
                .cartShareNm(cartShare.getCartShareNm())
                .build();
    }
}
