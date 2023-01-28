package com.ssg.sausagecartshareapi.cartshare.repository;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShareNoti;
import java.util.List;

public interface CartShareNotiRepositoryCustom {

    List<CartShareNoti> findReadNCartShareNotiListByMbrId(Long mbrId);
}
