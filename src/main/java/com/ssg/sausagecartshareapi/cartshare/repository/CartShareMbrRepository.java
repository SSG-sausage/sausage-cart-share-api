package com.ssg.sausagecartshareapi.cartshare.repository;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShare;
import com.ssg.sausagecartshareapi.cartshare.entity.CartShareMbr;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartShareMbrRepository extends JpaRepository<CartShareMbr, Long> {

    Optional<CartShareMbr> findCartShareMbrByCartShareAndMbrId(CartShare cartShare, Long mbrId);
}
