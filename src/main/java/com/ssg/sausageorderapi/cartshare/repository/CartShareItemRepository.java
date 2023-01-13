package com.ssg.sausageorderapi.cartshare.repository;

import com.ssg.sausageorderapi.cartshare.entity.CartShare;
import com.ssg.sausageorderapi.cartshare.entity.CartShareItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartShareItemRepository extends JpaRepository<CartShareItem, Long> {

    List<CartShareItem> findAllByCartShare(CartShare cartShare);

    Optional<CartShareItem> findByCartShareAndMbrIdAndItemId(CartShare cartShare, Long mbrId, Long itemId);
}
