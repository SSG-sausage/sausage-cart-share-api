package com.ssg.sausagecartshareapi.cartshare.entity;

import com.ssg.sausagecartshareapi.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

@Table(name = "CART_SHARE_MBR")
@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CartShareMbr extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARD_SHARE_MBR_ID")
    private Long cartShareMbrId;

    @Column(name = "MBR_ID")
    private Long mbrId;

    @ManyToOne
    @JoinColumn(name = "CART_SHARE_ID")
    private CartShare cartShare;

    @Column(name = "PROG_STAT_CD")
    @Enumerated(EnumType.STRING)
    private ProgStatCd progStatCd;

    public static CartShareMbr newInstance() {
        return CartShareMbr.builder()
                .build();
    }

    public void updateProgStatCd(ProgStatCd progStatCd) {
        this.progStatCd = progStatCd;
    }
}