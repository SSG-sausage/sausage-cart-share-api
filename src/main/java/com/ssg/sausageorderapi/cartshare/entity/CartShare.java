package com.ssg.sausageorderapi.cartshare.entity;

import com.ssg.sausageorderapi.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Table(name = "CART_SHARE")
@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CartShare extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_SHARE_ID")
    private Long cartShareId;

    @Column(name = "MASTR_MBR_ID")
    private Long mastrMbrId;

    @Column(name = "CART_SHARE_NM")
    private String cartShareNm;

    @Column(name = "EDIT_PSBL_YN")
    private Boolean editPsblYn;

    @Column(name = "CART_SHARE_ADDR")
    private String cartShareAddr;

    public static CartShare newInstance() {
        return CartShare.builder()
                .build();
    }

    public void updateEditPsblYn(boolean editPsblYn) {
        this.editPsblYn = editPsblYn;
    }
}