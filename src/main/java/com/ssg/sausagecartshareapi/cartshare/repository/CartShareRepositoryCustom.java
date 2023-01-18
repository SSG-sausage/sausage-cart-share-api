package com.ssg.sausagecartshareapi.cartshare.repository;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShare;
import java.util.List;

public interface CartShareRepositoryCustom {

    List<CartShare> findCartShareListByMbrId(Long mbrId);
}
