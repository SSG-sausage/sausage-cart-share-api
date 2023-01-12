package com.ssg.sausageorderapi.cartshare.repository;

import static com.ssg.sausageorderapi.cartshare.entity.QCartShareMbr.cartShareMbr;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.sausageorderapi.cartshare.entity.CartShare;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartShareRepositoryImpl implements CartShareRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CartShare> findCartShareListByMbrId(Long mbrId) {
        return queryFactory.select(cartShareMbr.cartShare)
                .from(cartShareMbr)
                .where(
                        cartShareMbr.mbrId.eq(mbrId)
                ).fetch();
    }
}
