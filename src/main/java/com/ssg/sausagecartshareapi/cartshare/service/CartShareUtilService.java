package com.ssg.sausagecartshareapi.cartshare.service;

import com.ssg.sausagecartshareapi.cartshare.entity.CartShare;
import com.ssg.sausagecartshareapi.cartshare.entity.CartShareItem;
import com.ssg.sausagecartshareapi.cartshare.entity.CartShareMbr;
import com.ssg.sausagecartshareapi.cartshare.repository.CartShareItemRepository;
import com.ssg.sausagecartshareapi.cartshare.repository.CartShareMbrRepository;
import com.ssg.sausagecartshareapi.cartshare.repository.CartShareRepository;
import com.ssg.sausagecartshareapi.common.exception.ErrorCode;
import com.ssg.sausagecartshareapi.common.exception.NotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartShareUtilService {

    private final CartShareRepository cartShareRepository;
    private final CartShareItemRepository cartShareItemRepository;
    private final CartShareMbrRepository cartShareMbrRepository;

    public CartShare findCartShareById(Long cartShareId) {
        Optional<CartShare> cartShare = cartShareRepository.findById(cartShareId);
        return cartShare.orElseThrow(
                () -> new NotFoundException(String.format("존재하지 않는 CART_SHARE (%s) 입니다.", cartShareId),
                        ErrorCode.NOT_FOUND_CART_SHARE_EXCEPTION));
    }

    public CartShareItem findCartShareItemById(Long cartShareItemId) {
        Optional<CartShareItem> cartShareItem = cartShareItemRepository.findById(cartShareItemId);
        return cartShareItem.orElseThrow(
                () -> new NotFoundException(String.format("존재하지 않는 CART_SHARE_ITEM (%s) 입니다.", cartShareItemId),
                        ErrorCode.NOT_FOUND_CART_SHARE_ITEM_EXCEPTION));
    }

    public CartShareMbr findCartShareMbrById(Long cartShareMbrId) {
        Optional<CartShareMbr> cartShareMbr = cartShareMbrRepository.findById(cartShareMbrId);
        return cartShareMbr.orElseThrow(
                () -> new NotFoundException(String.format("존재하지 않는 CART_SHARE_MBR (%s) 입니다.", cartShareMbrId),
                        ErrorCode.NOT_FOUND_CART_SHARE_MBR_EXCEPTION));
    }

    public CartShareMbr findCartShareMbrByCartShareAndMbrId(CartShare cartShare, Long mbrId) {
        Optional<CartShareMbr> cartShareMbr = cartShareMbrRepository.findCartShareMbrByCartShareAndMbrId(cartShare,
                mbrId);
        return cartShareMbr.orElseThrow(
                () -> new NotFoundException(String.format("존재하지 않는 멤버 (%s) 공유장바구니 (%s) 의 CART_SHARE_MBR 입니다.",
                        mbrId, cartShare.getCartShareId()),
                        ErrorCode.NOT_FOUND_CART_SHARE_MBR_EXCEPTION));
    }
}
