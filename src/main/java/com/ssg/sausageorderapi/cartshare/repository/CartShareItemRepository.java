package com.ssg.sausageorderapi.cartshare.repository;

import com.ssg.sausageorderapi.cartshare.entity.CartShareItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartShareItemRepository extends JpaRepository<CartShareItem, Long>, CartShareItemRepositoryCustom {

}
