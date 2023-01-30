package com.ssg.sausagecartshareapi.cartshare.repository;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShare;
import com.ssg.sausagecartshareapi.cartshare.entity.CartShareItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartShareItemRepository extends JpaRepository<CartShareItem, Long> {

    Optional<CartShareItem> findByCartShareAndMbrIdAndItemId(CartShare cartShare, Long mbrId, Long itemId);
}
