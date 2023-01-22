package com.ssg.sausagecartshareapi.cartshare.repository;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShare;
import com.ssg.sausagecartshareapi.cartshare.entity.CartShareItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartShareItemRepository extends JpaRepository<CartShareItem, Long> {

    List<CartShareItem> findAllByCartShare(CartShare cartShare);

    Optional<CartShareItem> findByCartShareAndMbrIdAndItemId(CartShare cartShare, Long mbrId, Long itemId);
}
