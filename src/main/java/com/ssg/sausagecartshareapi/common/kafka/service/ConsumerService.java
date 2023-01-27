package com.ssg.sausagecartshareapi.common.kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.sausagecartshareapi.cartshare.dto.CartShareUpdateDto;
import com.ssg.sausagecartshareapi.cartshare.entity.CartShare;
import com.ssg.sausagecartshareapi.cartshare.service.CartShareUtilService;
import com.ssg.sausagecartshareapi.common.exception.ErrorCode;
import com.ssg.sausagecartshareapi.common.exception.InternalServerException;
import com.ssg.sausagecartshareapi.common.kafka.constant.KafkaConstants;
import com.ssg.sausagecartshareapi.common.kafka.dto.CartShareUpdateEditPsblYnDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsumerService {

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
}
