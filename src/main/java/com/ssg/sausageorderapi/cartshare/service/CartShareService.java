package com.ssg.sausageorderapi.cartshare.service;

import com.ssg.sausageorderapi.cartshare.dto.response.CartShareFindListResponse;
import com.ssg.sausageorderapi.cartshare.dto.response.CartShareFindResponse;
import com.ssg.sausageorderapi.cartshare.entity.CartShare;
import com.ssg.sausageorderapi.cartshare.entity.CartShareItem;
import com.ssg.sausageorderapi.cartshare.entity.CartShareMbr;
import com.ssg.sausageorderapi.cartshare.repository.CartShareItemRepository;
import com.ssg.sausageorderapi.cartshare.repository.CartShareMbrRepository;
import com.ssg.sausageorderapi.cartshare.repository.CartShareRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

    public CartShareFindListResponse findCartShareList(Long mbrId) {
        List<CartShare> cartShareList = cartShareRepository.findCartShareListByMbrId(mbrId);
        return CartShareFindListResponse.of(cartShareList);
    }

    public CartShareFindResponse findCartShare(Long cartShareId, Long mbrId) {
        CartShare cartShare = cartShareUtilService.findCartShareById(cartShareId);
        cartShareUtilService.validateCartShareMbr(cartShare, mbrId);
        List<CartShareMbr> cartShareMbrList = cartShareMbrRepository.findAllByCartShare(cartShare);
        List<CartShareItem> cartShareItemList = cartShareItemRepository.findAllByCartShare(cartShare);
        return CartShareFindResponse.of(cartShare, cartShareMbrList, cartShareItemList);
    }
}
