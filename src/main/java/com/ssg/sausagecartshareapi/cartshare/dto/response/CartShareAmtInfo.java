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
        List<CartShareItem> ssgCartShareItemList = filterByShppCd("SSG_SHPP", cartShareItemList, itemInfoMap);
        List<CartShareItem> tradersCartShareItemList = filterByShppCd("SSG_TRADERS_SHPP", cartShareItemList,
                itemInfoMap);

        // 쓱배송 상품 주문금액
        int ssgOrdAmt = ssgCartShareItemList.stream()
                .mapToInt(item -> item.getItemQty() * itemInfoMap.get(item.getItemId()).getItemAmt())
                .sum();

        // 쓱배송 상품 배송비
        int ssgShppAmt = ssgOrdAmt >= 40000 || ssgOrdAmt == 0 ? 0 : 3000;

        // 쓱배송 무료배송까지 남은 금액
        int ssgFreeShppRemainAmt = ssgOrdAmt >= 40000 ? 0 : 40000 - ssgOrdAmt;

        // 트레이더스 상품 주문금액
        int tradersOrdAmt = tradersCartShareItemList.stream()
                .mapToInt(item -> item.getItemQty() * itemInfoMap.get(item.getItemId()).getItemAmt())
                .sum();

        // 트레이더스 상품 배송비
        int tradersShppAmt = tradersOrdAmt >= 120000 || tradersOrdAmt == 0 ? 0 : 4000;

        // 트레이더스 무료배송까지 남은 금액
        int tradersFreeShppRemainAmt = tradersOrdAmt >= 120000 ? 0 : 120000 - tradersOrdAmt;

        // 주문 금액
        int ordAmt = ssgOrdAmt + tradersOrdAmt;

        // 배송비
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
                .itemQty(countUniqueItemQty(cartShareItemList))
                .build();
    }

    private static List<CartShareItem> filterByShppCd(String shppCd, List<CartShareItem> cartShareItemList,
            Map<Long, ItemInfo> itemInfoMap) {
        return cartShareItemList.stream()
                .filter(cartShareItem -> itemInfoMap.get(cartShareItem.getItemId())
                        .getShppCd().equals(shppCd))
                .collect(Collectors.toList());
    }

    private static int countUniqueItemQty(List<CartShareItem> cartShareItemList) {
        return cartShareItemList.stream().map(CartShareItem::getItemId).collect(Collectors.toSet()).size();
    }
}
