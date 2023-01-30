package com.ssg.sausagecartshareapi.cartshare.dto.response;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShareItem;
import com.ssg.sausagecartshareapi.common.client.internal.dto.response.ItemListInfoResponse.ItemInfo;
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
public class CartShareAmtInfo {

    @Schema(description = "쓱배송 주문금액")
    private int ssgOrdAmt;

    @Schema(description = "쓱배송 배송비")
    private int ssgShppAmt;

    @Schema(description = "쓱배송 총금액")
    private int ssgTotalAmt;

    @Schema(description = "쓱배송 무료배송까지 남은 금액")
    private int ssgFreeShppRemainAmt;

    @Schema(description = "트레이더스 주문금액")
    private int tradersOrdAmt;

    @Schema(description = "트레이더스 배송비")
    private int tradersShppAmt;

    @Schema(description = "트레이더스 총금액")
    private int tradersTotalAmt;

    @Schema(description = "트레이더스 무료배송까지 남은 금액")
    private int tradersFreeShppRemainAmt;

    @Schema(description = "주문금액")
    private int ordAmt;

    @Schema(description = "상품할인")
    private int discountAmt;

    @Schema(description = "배송비")
    private int shppAmt;

    @Schema(description = "총 결제 예정 금액")
    private int totalAmt;

    @Schema(description = "총 상품 개수")
    private int itemQty;

    public static CartShareAmtInfo of(List<CartShareItem> cartShareItemList, Map<Long, ItemInfo> itemInfoMap) {
        List<CartShareItem> ssgCartShareItemList = cartShareItemList.stream()
                .filter(cartShareItem -> itemInfoMap.get(cartShareItem.getItemId())
                        .getShppCd().equals("SSG_SHPP"))
                .collect(Collectors.toList());
        List<CartShareItem> tradersCartShareItemList = cartShareItemList.stream()
                .filter(cartShareItem -> itemInfoMap.get(cartShareItem.getItemId())
                        .getShppCd().equals("SSG_TRADERS_SHPP"))
                .collect(Collectors.toList());
        int ssgOrdAmt = ssgCartShareItemList.stream().mapToInt(ssgCartShareItem ->
                        ssgCartShareItem.getItemQty() * itemInfoMap.get(ssgCartShareItem.getItemId()).getItemAmt())
                .sum();
        int ssgShppAmt = ssgOrdAmt >= 40000 ? 0 : 3000;
        int ssgFreeShppRemainAmt = ssgOrdAmt >= 40000 ? 0 : 40000 - ssgOrdAmt;
        int tradersOrdAmt = tradersCartShareItemList.stream().mapToInt(tradersCartShareItem ->
                        tradersCartShareItem.getItemQty() * itemInfoMap.get(tradersCartShareItem.getItemId()).getItemAmt())
                .sum();
        int tradersShppAmt = tradersOrdAmt >= 120000 ? 0 : 4000;
        int tradersFreeShppRemainAmt = tradersOrdAmt >= 120000 ? 0 : 120000 - tradersOrdAmt;
        int ordAmt = ssgOrdAmt + tradersOrdAmt;
        int shppAmt = ssgShppAmt + tradersShppAmt;
        return CartShareAmtInfo.builder()
                .ssgOrdAmt(ssgOrdAmt)
                .ssgShppAmt(ssgShppAmt)
                .ssgTotalAmt(ssgOrdAmt + ssgShppAmt)
                .ssgFreeShppRemainAmt(ssgFreeShppRemainAmt)
                .tradersOrdAmt(tradersOrdAmt)
                .tradersShppAmt(tradersShppAmt)
                .tradersTotalAmt(tradersOrdAmt + tradersShppAmt)
                .tradersFreeShppRemainAmt(tradersFreeShppRemainAmt)
                .ordAmt(ordAmt)
                .discountAmt(0)
                .shppAmt(shppAmt)
                .totalAmt(ordAmt + shppAmt)
                .itemQty(cartShareItemList.stream().map(CartShareItem::getItemId).collect(Collectors.toSet()).size())
                .build();
    }
}
