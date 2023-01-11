package com.ssg.sausageorderapi.cartshare.repository;

import com.ssg.sausageorderapi.cartshare.entity.CartShareMbr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartShareMbrRepository extends JpaRepository<CartShareMbr, Long>, CartShareMbrRepositoryCustom {

}
