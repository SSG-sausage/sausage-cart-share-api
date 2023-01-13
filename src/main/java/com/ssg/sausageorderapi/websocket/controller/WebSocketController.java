package com.ssg.sausageorderapi.websocket.controller;

import com.ssg.sausageorderapi.websocket.dto.CartShareUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/cart-share")
    public void updateCartShare(CartShareUpdateDto cartShareUpdateDto, SimpMessageHeaderAccessor accessor) {
        simpMessagingTemplate.convertAndSend(
                "/sub/cart-share/" + cartShareUpdateDto.getCartShareId(), cartShareUpdateDto);
    }
}
