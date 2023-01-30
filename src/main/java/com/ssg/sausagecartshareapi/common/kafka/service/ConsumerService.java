package com.ssg.sausagecartshareapi.common.kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.sausagecartshareapi.cartshare.dto.CartShareUpdateDto;
import com.ssg.sausagecartshareapi.cartshare.dto.MbrUpdateDto;
import com.ssg.sausagecartshareapi.cartshare.entity.CartShare;
import com.ssg.sausagecartshareapi.cartshare.entity.CartShareItem;
import com.ssg.sausagecartshareapi.cartshare.entity.CartShareMbr;
import com.ssg.sausagecartshareapi.cartshare.entity.CartShareNoti;
import com.ssg.sausagecartshareapi.cartshare.entity.NotiCd;
import com.ssg.sausagecartshareapi.cartshare.entity.ProgStatCd;
import com.ssg.sausagecartshareapi.cartshare.repository.CartShareItemRepository;
import com.ssg.sausagecartshareapi.cartshare.repository.CartShareMbrRepository;
import com.ssg.sausagecartshareapi.cartshare.repository.CartShareNotiRepository;
import com.ssg.sausagecartshareapi.cartshare.service.CartShareUtilService;
import com.ssg.sausagecartshareapi.common.exception.ErrorCode;
import com.ssg.sausagecartshareapi.common.exception.InternalServerException;
import com.ssg.sausagecartshareapi.common.kafka.constant.KafkaConstants;
import com.ssg.sausagecartshareapi.common.kafka.dto.CartShareItemDeleteListDto;
import com.ssg.sausagecartshareapi.common.kafka.dto.CartShareNotiCreateDto;
import com.ssg.sausagecartshareapi.common.kafka.dto.CartShareUpdateEditPsblYnDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final CartShareMbrRepository cartShareMbrRepository;
    private final CartShareItemRepository cartShareItemRepository;
    private final CartShareNotiRepository cartShareNotiRepository;
    private final CartShareUtilService cartShareUtilService;
    private final ObjectMapper objectMapper;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Transactional
    @KafkaListener(topics = KafkaConstants.KAFKA_CART_SHARE_EDIT_UPDATE, groupId = KafkaConstants.CONSUMER_GROUP_ID)
    public void updateCartShareEdit(String message) {
        try {
            CartShareUpdateEditPsblYnDto dto = objectMapper.readValue(message, CartShareUpdateEditPsblYnDto.class);
            CartShare cartShare = cartShareUtilService.findCartShareById(dto.getCartShareId());
            cartShare.updateEditPsblYn(dto.getEditPsblYn());
            simpMessagingTemplate.convertAndSend(
                    "/sub/cart-share/" + dto.getCartShareId(),
                    CartShareUpdateDto.of(dto.getCartShareId(), 0L, "update"));
        } catch (Exception exception) {
            throw new InternalServerException("예상치 못한 서버 에러가 발생하였습니다.", ErrorCode.INTERNAL_SERVER_EXCEPTION);
        }
    }

    @Transactional
    @KafkaListener(topics = KafkaConstants.KAFKA_CART_SHARE_ITEM_DELETE, groupId = KafkaConstants.CONSUMER_GROUP_ID)
    public void deleteCartShareItem(String message) {
        try {
            CartShareItemDeleteListDto dto = objectMapper.readValue(message, CartShareItemDeleteListDto.class);
            CartShare cartShare = cartShareUtilService.findCartShareById(dto.getCartShareId());
            List<CartShareItem> cartShareItemList = cartShare.getCartShareItemList();
            List<CartShareMbr> cartShareMbrList = cartShare.getCartShareMbrList();
            cartShareMbrList.forEach(cartShareMbr -> cartShareMbr.updateProgStatCd(ProgStatCd.IN_PROGRESS));
            cartShareItemRepository.deleteAllInBatch(cartShareItemList);
            simpMessagingTemplate.convertAndSend(
                    "/sub/cart-share/" + dto.getCartShareId(),
                    CartShareUpdateDto.of(dto.getCartShareId(), 0L, "update"));
        } catch (Exception exception) {
            throw new InternalServerException("예상치 못한 서버 에러가 발생하였습니다.", ErrorCode.INTERNAL_SERVER_EXCEPTION);
        }
    }

    @Transactional
    @KafkaListener(topics = KafkaConstants.KAFKA_CART_SHARE_NOTI_CREATE, groupId = KafkaConstants.CONSUMER_GROUP_ID)
    public void createCartShareNoti(String message) {
        try {
            CartShareNotiCreateDto dto = objectMapper.readValue(message, CartShareNotiCreateDto.class);
            cartShareNotiRepository.save(
                    CartShareNoti.newInstance(dto.getMbrId(), NotiCd.of(dto.getNotiCd()), dto.getCartShareNotiCntt()));
            simpMessagingTemplate.convertAndSend("/sub/mbr/" + dto.getMbrId(),
                    MbrUpdateDto.of(dto.getMbrId(), "update"));
        } catch (Exception exception) {
            throw new InternalServerException("예상치 못한 서버 에러가 발생하였습니다.", ErrorCode.INTERNAL_SERVER_EXCEPTION);
        }
    }
}
