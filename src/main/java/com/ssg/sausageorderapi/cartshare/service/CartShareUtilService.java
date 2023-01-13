package com.ssg.sausageorderapi.cartshare.service;

import com.ssg.sausageorderapi.cartshare.entity.CartShare;
import com.ssg.sausageorderapi.cartshare.entity.CartShareMbr;
import com.ssg.sausageorderapi.cartshare.repository.CartShareMbrRepository;
import com.ssg.sausageorderapi.cartshare.repository.CartShareRepository;
import com.ssg.sausageorderapi.common.exception.ErrorCode;
import com.ssg.sausageorderapi.common.exception.ForbiddenException;
import com.ssg.sausageorderapi.common.exception.NotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartShareUtilService {

    private final CartShareRepository cartShareRepository;
    private final CartShareMbrRepository cartShareMbrRepository;

    public CartShare findCartShareById(Long cartShareId) {
        Optional<CartShare> cartShare = cartShareRepository.findById(cartShareId);
        return cartShare.orElseThrow(
                () -> new NotFoundException(String.format("존재하지 않는 CART_SHARE (%s) 입니다.", cartShareId),
                        ErrorCode.NOT_FOUND_CART_SHARE_EXCEPTION));
    }

    public void validateCartShareMbr(CartShare cartShare, Long mbrId) {
        Optional<CartShareMbr> cartShareMbr = cartShareMbrRepository.findCartShareMbrByCartShareAndMbrId(
                cartShare, mbrId);
        if (cartShareMbr.isEmpty()) {
            throw new ForbiddenException(
                    String.format("멤버 (%s) 는 공유장바구니 (%s) 에 접근할 수 없습니다.", mbrId, cartShare.getCartShareId()),
                    ErrorCode.FORBIDDEN_CART_SHARE_ACCESS_EXCEPTION);
        }
    }
}
