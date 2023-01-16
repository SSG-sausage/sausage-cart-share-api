package com.ssg.sausageorderapi.cartshare.entity;

import com.ssg.sausageorderapi.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Table(name = "CART_SHARE_ITEM")
@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CartShareItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_SHARE_ITEM_ID")
    private Long cartShareItemId;

    @Column(name = "MBR_ID")
    private Long mbrId;

    @Column(name = "ITEM_ID")
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "CART_SHARE_ID")
    private CartShare cartShare;

    @Column(name = "COMM_YN")
    private Boolean commYn;

    @Column(name = "ITEM_QTY")
    private Integer itemQty;

    public static CartShareItem newInstance(Long mbrId, Long itemId, CartShare cartShare, int itemQty) {
        return CartShareItem.builder()
                .mbrId(mbrId)
                .itemId(itemId)
                .cartShare(cartShare)
                .commYn(false)
                .itemQty(itemQty)
                .build();
    }

    public void addItemQty(int itemQty) {
        this.itemQty += itemQty;
    }

    public void updateCommYn(boolean commYn) {
        this.commYn = commYn;
    }
}