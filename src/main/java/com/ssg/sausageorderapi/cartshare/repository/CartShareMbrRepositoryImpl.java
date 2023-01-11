package com.ssg.sausageorderapi.cartshare.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartShareMbrRepositoryImpl implements CartShareMbrRepositoryCustom {

    private final JPAQueryFactory queryFactory;
}
