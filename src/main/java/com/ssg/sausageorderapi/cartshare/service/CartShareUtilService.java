package com.ssg.sausageorderapi.cartshare.service;

import com.ssg.sausageorderapi.cartshare.entity.CartShare;
import com.ssg.sausageorderapi.cartshare.repository.CartShareRepository;
import com.ssg.sausageorderapi.common.exception.ErrorCode;
import com.ssg.sausageorderapi.common.exception.NotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartShareUtilService {

    private final CartShareRepository cartShareRepository;

    public CartShare findCartShareById(Long cartShareId) {
        Optional<CartShare> cartShare = cartShareRepository.findById(cartShareId);
        return cartShare.orElseThrow(
                () -> new NotFoundException(String.format("존재하지 않는 CART_SHARE (%s) 입니다.", cartShareId),
                        ErrorCode.NOT_FOUND_CART_SHARE_EXCEPTION));
    }
}
