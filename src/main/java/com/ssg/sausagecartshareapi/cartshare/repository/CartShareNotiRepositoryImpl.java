package com.ssg.sausagecartshareapi.cartshare.repository;

import static com.ssg.sausagecartshareapi.cartshare.entity.QCartShareNoti.cartShareNoti;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.sausagecartshareapi.cartshare.entity.CartShareNoti;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartShareNotiRepositoryImpl implements CartShareNotiRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CartShareNoti> findReadNCartShareNotiListByMbrId(Long mbrId) {
        return queryFactory.selectFrom(cartShareNoti)
                .where(
                        cartShareNoti.mbrId.eq(mbrId),
                        cartShareNoti.readYn.eq(false)
                )
                .fetch();
    }
}
