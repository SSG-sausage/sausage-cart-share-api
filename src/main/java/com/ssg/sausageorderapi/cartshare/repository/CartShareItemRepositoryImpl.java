package com.ssg.sausageorderapi.cartshare.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartShareItemRepositoryImpl implements CartShareItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;
}
