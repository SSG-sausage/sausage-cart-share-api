package com.ssg.sausageorderapi.cartshare.service;

import com.ssg.sausageorderapi.cartshare.dto.CartShareUpdateDto;
import com.ssg.sausageorderapi.cartshare.dto.request.CartShareItemSaveRequest;
import com.ssg.sausageorderapi.cartshare.dto.response.CartShareFindListResponse;
import com.ssg.sausageorderapi.cartshare.dto.response.CartShareFindResponse;
import com.ssg.sausageorderapi.cartshare.entity.CartShare;
import com.ssg.sausageorderapi.cartshare.entity.CartShareItem;
import com.ssg.sausageorderapi.cartshare.entity.CartShareMbr;
import com.ssg.sausageorderapi.cartshare.repository.CartShareItemRepository;
import com.ssg.sausageorderapi.cartshare.repository.CartShareMbrRepository;
import com.ssg.sausageorderapi.cartshare.repository.CartShareRepository;
import com.ssg.sausageorderapi.common.exception.ErrorCode;
import com.ssg.sausageorderapi.common.exception.ForbiddenException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartShareService {

    private final CartShareRepository cartShareRepository;
    private final CartShareMbrRepository cartShareMbrRepository;
    private final CartShareItemRepository cartShareItemRepository;
    private final CartShareUtilService cartShareUtilService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public CartShareFindListResponse findCartShareList(Long mbrId) {
        List<CartShare> cartShareList = cartShareRepository.findCartShareListByMbrId(mbrId);
        return CartShareFindListResponse.of(cartShareList);
    }

    public CartShareFindResponse findCartShare(Long cartShareId, Long mbrId) {
        CartShare cartShare = cartShareUtilService.findCartShareById(cartShareId);
        validateCartShareMbr(cartShare, mbrId);
        List<CartShareMbr> cartShareMbrList = cartShareMbrRepository.findAllByCartShare(cartShare);
        List<CartShareItem> cartShareItemList = cartShareItemRepository.findAllByCartShare(cartShare);
        return CartShareFindResponse.of(cartShare, cartShareMbrList, cartShareItemList);
    }

    @Transactional
    public void saveCartShareItem(Long cartShareId, Long mbrId, CartShareItemSaveRequest request) {
        CartShare cartShare = cartShareUtilService.findCartShareById(cartShareId);
        validateCartShareMbr(cartShare, mbrId);
        Optional<CartShareItem> cartShareItem = cartShareItemRepository.findByCartShareAndMbrIdAndItemId(
                cartShare, mbrId, request.getItemId());
        if (cartShareItem.isPresent()) {
            cartShareItem.get().addItemQty(request.getItemQty());
        } else {
            cartShareItemRepository.save(
                    CartShareItem.newInstance(mbrId, request.getItemId(), cartShare, request.getItemQty()));
        }
        simpMessagingTemplate.convertAndSend(
                "/sub/cart-share/" + cartShareId, CartShareUpdateDto.of(cartShareId, mbrId, "update"));
    }

    private void validateCartShareMbr(CartShare cartShare, Long mbrId) {
        Optional<CartShareMbr> cartShareMbr = cartShareMbrRepository.findCartShareMbrByCartShareAndMbrId(
                cartShare, mbrId);
        if (cartShareMbr.isEmpty()) {
            throw new ForbiddenException(
                    String.format("멤버 (%s) 는 공유장바구니 (%s) 에 접근할 수 없습니다.", mbrId, cartShare.getCartShareId()),
                    ErrorCode.FORBIDDEN_CART_SHARE_ACCESS_EXCEPTION);
        }
    }
}
