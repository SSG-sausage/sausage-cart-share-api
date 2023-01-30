package com.ssg.sausagecartshareapi.common.websocket;

import com.ssg.sausagecartshareapi.cartshare.dto.CartShareUpdateDto;
import com.ssg.sausagecartshareapi.cartshare.dto.MbrUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void sendCartShareUpdateMessage(Long cartShareId, Long mbrId) {
        simpMessagingTemplate.convertAndSend(
                "/sub/cart-share/" + cartShareId, CartShareUpdateDto.of(cartShareId, mbrId, "update"));
    }

    public void sendMbrUpdateMessage(Long mbrId) {
        simpMessagingTemplate.convertAndSend("/sub/mbr/" + mbrId, MbrUpdateDto.of(mbrId, "update"));
    }
}
