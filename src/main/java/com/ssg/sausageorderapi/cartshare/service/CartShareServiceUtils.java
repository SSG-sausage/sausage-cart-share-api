package com.ssg.sausageorderapi.cartshare.service;

import com.ssg.sausageorderapi.cartshare.entity.CartShare;
import com.ssg.sausageorderapi.cartshare.entity.CartShareMbr;
import com.ssg.sausageorderapi.cartshare.repository.CartShareMbrRepository;
import com.ssg.sausageorderapi.cartshare.repository.CartShareRepository;
import com.ssg.sausageorderapi.common.exception.ErrorCode;
import com.ssg.sausageorderapi.common.exception.ForbiddenException;
import com.ssg.sausageorderapi.common.exception.NotFoundException;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartShareServiceUtils {

    public static CartShare findCartShareById(CartShareRepository cartShareRepository, Long cartShareId) {
        Optional<CartShare> cartShare = cartShareRepository.findById(cartShareId);
        if (cartShare.isEmpty()) {
            throw new NotFoundException(String.format("존재하지 않는 CART_SHARE (%s) 입니다.", cartShareId),
                    ErrorCode.NOT_FOUND_CART_SHARE_EXCEPTION);
        }
        return cartShare.get();
    }

    public static void validateCartShareMbr(CartShareMbrRepository cartShareMbrRepository, CartShare cartShare,
            Long mbrId) {
        CartShareMbr cartShareMbr = cartShareMbrRepository.findCartShareMbrByCartShareAndMbrId(cartShare, mbrId);
        if (cartShareMbr == null) {
            throw new ForbiddenException(
                    String.format("멤버 (%s) 는 공유장바구니 (%s) 에 접근할 수 없습니다.", mbrId, cartShare.getCartShareId()),
                    ErrorCode.FORBIDDEN_CART_SHARE_ACCESS_EXCEPTION);
        }
    }
}
