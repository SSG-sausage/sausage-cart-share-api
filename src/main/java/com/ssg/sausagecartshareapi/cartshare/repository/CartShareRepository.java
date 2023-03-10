package com.ssg.sausagecartshareapi.cartshare.repository;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartShareRepository extends JpaRepository<CartShare, Long>, CartShareRepositoryCustom {

}
