package com.ssg.sausagecartshareapi.cartshare.repository;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShareNoti;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartShareNotiRepository extends JpaRepository<CartShareNoti, Long>, CartShareNotiRepositoryCustom {

    List<CartShareNoti> findAllByMbrId(Long mbrId);
}
