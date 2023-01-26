package com.ssg.sausagecartshareapi.cartshare.entity;

import com.ssg.sausagecartshareapi.common.entity.BaseEntity;
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

@Table(name = "CART_SHARE_NOTI")
@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CartShareNoti extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_SHARE_NOTI_ID")
    private Long cartShareNotiId;

    @Column(name = "MBR_ID")
    private Long mbrId;

    @Column(name = "CART_SHARE_NOTI_CNTT")
    private String cartShareNotiCntt;

    @Column(name = "READ_YN")
    private Boolean readYn;
}