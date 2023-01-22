package com.ssg.sausagecartshareapi.cartshare.controller;

import com.ssg.sausagecartshareapi.cartshare.dto.CartShareUpdateDto;
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
