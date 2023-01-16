package com.ssg.sausageorderapi.cartshare.service;

import com.ssg.sausageorderapi.cartshare.dto.CartShareUpdateDto;
import com.ssg.sausageorderapi.cartshare.dto.request.CartShareEditUpdateRequest;
import com.ssg.sausageorderapi.cartshare.dto.request.CartShareItemCommUpdateRequest;
import com.ssg.sausageorderapi.cartshare.dto.request.CartShareItemQtyUpdateRequest;
import com.ssg.sausageorderapi.cartshare.dto.request.CartShareItemSaveRequest;
import com.ssg.sausageorderapi.cartshare.dto.request.CartShareMbrProgUpdateRequest;
import com.ssg.sausageorderapi.cartshare.dto.response.CartShareFindListResponse;
import com.ssg.sausageorderapi.cartshare.dto.response.CartShareFindResponse;
import com.ssg.sausageorderapi.cartshare.entity.CartShare;
import com.ssg.sausageorderapi.cartshare.entity.CartShareItem;
import com.ssg.sausageorderapi.cartshare.entity.CartShareMbr;
import com.ssg.sausageorderapi.cartshare.entity.ProgStatCd;
import com.ssg.sausageorderapi.cartshare.repository.CartShareItemRepository;
import com.ssg.sausageorderapi.cartshare.repository.CartShareMbrRepository;
import com.ssg.sausageorderapi.cartshare.repository.CartShareRepository;
import com.ssg.sausageorderapi.common.exception.ErrorCode;
import com.ssg.sausageorderapi.common.exception.ForbiddenException;
import com.ssg.sausageorderapi.common.exception.ValidationException;
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
        CartShareMbr cartShareMbr = findCartShareMbrByCartShareAndMbrId(cartShare, mbrId);
        validateCartShareEdit(cartShare);
        validateCartShareMbrProg(cartShareMbr);
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

    @Transactional
    public void deleteCartShareItem(Long cartShareId, Long cartShareItemId, Long mbrId) {
        CartShare cartShare = cartShareUtilService.findCartShareById(cartShareId);
        CartShareItem cartShareItem = cartShareUtilService.findCartShareItemById(cartShareItemId);
        CartShareMbr cartShareMbr = findCartShareMbrByCartShareAndMbrId(cartShare, mbrId);
        validateCartShareEdit(cartShare);
        validateCartShareMbrProg(cartShareMbr);
        validateCartShareItem(cartShareItem, cartShare, mbrId);
        cartShareItemRepository.delete(cartShareItem);
        simpMessagingTemplate.convertAndSend(
                "/sub/cart-share/" + cartShareId, CartShareUpdateDto.of(cartShareId, mbrId, "update"));
    }

    @Transactional
    public void updateCartShareItemQty(Long cartShareId, Long cartShareItemId, Long mbrId,
            CartShareItemQtyUpdateRequest request) {
        CartShare cartShare = cartShareUtilService.findCartShareById(cartShareId);
        CartShareItem cartShareItem = cartShareUtilService.findCartShareItemById(cartShareItemId);
        CartShareMbr cartShareMbr = findCartShareMbrByCartShareAndMbrId(cartShare, mbrId);
        validateCartShareEdit(cartShare);
        validateCartShareMbrProg(cartShareMbr);
        validateCartShareItem(cartShareItem, cartShare, mbrId);
        validateCartShareItemQty(cartShareItem, request.getQty());
        cartShareItem.addItemQty(request.getQty());
        simpMessagingTemplate.convertAndSend(
                "/sub/cart-share/" + cartShareId, CartShareUpdateDto.of(cartShareId, mbrId, "update"));
    }

    @Transactional
    public void updateCartShareItemComm(Long cartShareId, Long cartShareItemId, Long mbrId,
            CartShareItemCommUpdateRequest request) {
        CartShare cartShare = cartShareUtilService.findCartShareById(cartShareId);
        CartShareItem cartShareItem = cartShareUtilService.findCartShareItemById(cartShareItemId);
        CartShareMbr cartShareMbr = findCartShareMbrByCartShareAndMbrId(cartShare, mbrId);
        validateCartShareEdit(cartShare);
        validateCartShareMbrProg(cartShareMbr);
        validateCartShareMastr(cartShare, mbrId);
        validateCartShareItem(cartShareItem, cartShare, mbrId);
        validateCartShareItemComm(cartShareItem, request.isCommYn());
        cartShareItem.updateCommYn(request.isCommYn());
        simpMessagingTemplate.convertAndSend(
                "/sub/cart-share/" + cartShareId, CartShareUpdateDto.of(cartShareId, mbrId, "update"));
    }

    @Transactional
    public void updateCartShareMbrProg(Long cartShareId, Long cartShareMbrId, Long mbrId,
            CartShareMbrProgUpdateRequest request) {
        CartShare cartShare = cartShareUtilService.findCartShareById(cartShareId);
        CartShareMbr cartShareMbr = cartShareUtilService.findCartShareMbrById(cartShareMbrId);
        validateCartShareEdit(cartShare);
        validateCartShareMbr(cartShare, mbrId);
        validateCartShareMbrProg(cartShareMbr, request.getProgStatCd());
        cartShareMbr.updateProgStatCd(request.getProgStatCd());
        simpMessagingTemplate.convertAndSend(
                "/sub/cart-share/" + cartShareId, CartShareUpdateDto.of(cartShareId, mbrId, "update"));
    }

    @Transactional
    public void updateCartShareEdit(Long cartShareId, Long mbrId, CartShareEditUpdateRequest request) {
        CartShare cartShare = cartShareUtilService.findCartShareById(cartShareId);
        validateCartShareMbr(cartShare, mbrId);
        validateCartShareMastr(cartShare, mbrId);
        validateCartShareEdit(cartShare, request.isEditPsblYn());
        cartShare.updateEditPsblYn(request.isEditPsblYn());
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

    private void validateCartShareItem(CartShareItem cartShareItem, CartShare cartShare, Long mbrId) {
        if (!cartShareItem.getCartShare().equals(cartShare) || !cartShareItem.getMbrId().equals(mbrId)) {
            throw new ForbiddenException(
                    String.format("멤버 (%s) 는 공유장바구니상품 (%s) 에 접근할 수 없습니다.", mbrId, cartShareItem.getCartShareItemId()),
                    ErrorCode.FORBIDDEN_CART_SHARE_ITEM_ACCESS_EXCEPTION);
        }
    }

    private void validateCartShareItemQty(CartShareItem cartShareItem, int qty) {
        if (cartShareItem.getItemQty() + qty < 1) {
            throw new ValidationException(
                    String.format("공유장바구니상품의 수량 (%s) 에 수량 (%s) 를 더할 수 없습니다.", cartShareItem.getItemQty(), qty),
                    ErrorCode.VALIDATION_CART_SHARE_ITEM_QTY_EXCEPTION);
        }
    }

    private void validateCartShareItemComm(CartShareItem cartShareItem, boolean commYn) {
        if (cartShareItem.getCommYn().equals(commYn)) {
            throw new ValidationException(
                    String.format("공유장바구니상품의 상태 (%s) 와 요청한 상태 (%s) 가 같습니다.", cartShareItem.getCommYn(), commYn),
                    ErrorCode.VALIDATION_CART_SHARE_ITEM_COMM_EXCEPTION);
        }
    }

    private void validateCartShareMastr(CartShare cartShare, Long mbrId) {
        if (!cartShare.getMastrMbrId().equals(mbrId)) {
            throw new ForbiddenException(
                    String.format("공유장바구니의 마스터 (%s) 가 아닌 멤버 (%s) 는 접근 권한이 없습니다.", cartShare.getMastrMbrId(), mbrId),
                    ErrorCode.FORBIDDEN_CART_SHARE_MASTR_ACCESS_EXCEPTION);
        }
    }

    private void validateCartShareMbrProg(CartShareMbr cartShareMbr, ProgStatCd progStatCd) {
        if (cartShareMbr.getProgStatCd().equals(progStatCd)) {
            throw new ValidationException(
                    String.format("공유장바구니멤버의 진행상태 (%s) 와 요청한 상태 (%s) 가 같습니다.", cartShareMbr.getProgStatCd(),
                            progStatCd),
                    ErrorCode.VALIDATION_CART_SHARE_MBR_PROG_EXCEPTION);
        }
    }

    private void validateCartShareMbrProg(CartShareMbr cartShareMbr) {
        if (!cartShareMbr.getProgStatCd().equals(ProgStatCd.IN_PROGRESS)) {
            throw new ValidationException(
                    String.format("공유장바구니멤버의 진행상태가 (%s) 이라서 수정할 수 없습니다.", cartShareMbr.getProgStatCd()),
                    ErrorCode.VALIDATION_CART_SHARE_MBR_PROG_DONE_EXCEPTION);
        }
    }

    private CartShareMbr findCartShareMbrByCartShareAndMbrId(CartShare cartShare, Long mbrId) {
        Optional<CartShareMbr> cartShareMbr = cartShareMbrRepository.findCartShareMbrByCartShareAndMbrId(
                cartShare, mbrId);
        return cartShareMbr.orElseThrow(
                () -> new ForbiddenException(
                        String.format("멤버 (%s) 는 공유장바구니 (%s) 에 접근할 수 없습니다.", mbrId, cartShare.getCartShareId()),
                        ErrorCode.FORBIDDEN_CART_SHARE_ACCESS_EXCEPTION));
    }

    private void validateCartShareEdit(CartShare cartShare, boolean editPsblYn) {
        if (cartShare.getEditPsblYn().equals(editPsblYn)) {
            throw new ValidationException(
                    String.format("공유장바구니의 수정가능여부 (%s) 와 요청한 상태 (%s) 가 같습니다.", cartShare.getEditPsblYn(), editPsblYn),
                    ErrorCode.VALIDATION_CART_SHARE_EDIT_EXCEPTION);
        }
    }

    private void validateCartShareEdit(CartShare cartShare) {
        if (cartShare.getEditPsblYn().equals(false)) {
            throw new ValidationException(
                    String.format("공유장바구니의 수정가능여부가 (%s) 라서 수정할 수 없습니다.", cartShare.getEditPsblYn()),
                    ErrorCode.VALIDATION_CART_SHARE_EDIT_PSBL_TRUE_EXCEPTION);
        }
    }
}
