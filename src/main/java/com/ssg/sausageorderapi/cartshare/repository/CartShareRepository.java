package com.ssg.sausageorderapi.cartshare.repository;

import com.ssg.sausageorderapi.cartshare.entity.CartShare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartShareRepository extends JpaRepository<CartShare, Long>, CartShareRepositoryCustom {

}
