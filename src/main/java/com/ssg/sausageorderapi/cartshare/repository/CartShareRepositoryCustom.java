package com.ssg.sausageorderapi.cartshare.repository;

import com.ssg.sausageorderapi.cartshare.entity.CartShare;
import java.util.List;

public interface CartShareRepositoryCustom {

    List<CartShare> findCartShareListByMbrId(Long mbrId);
}
