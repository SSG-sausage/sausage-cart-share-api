package com.ssg.sausageorderapi.cartshare.controller;

import com.ssg.sausageorderapi.cartshare.dto.response.CartShareFindListResponse;
import com.ssg.sausageorderapi.cartshare.service.CartShareService;
import com.ssg.sausageorderapi.common.config.resolver.MbrId;
import com.ssg.sausageorderapi.common.dto.ErrorResponse;
import com.ssg.sausageorderapi.common.dto.SuccessResponse;
import com.ssg.sausageorderapi.common.success.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CartShare", description = "공유장바구니")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CartShareController {

    private final CartShareService cartShareService;

    @Operation(summary = "장바구니 리스트 조회", responses = {
            @ApiResponse(responseCode = "200", description = "장바구니 리스트 조회 성공입니다."),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러가 발생하였습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/cart-share")
    public ResponseEntity<SuccessResponse<CartShareFindListResponse>> findCartShareList(
            @Parameter(in = ParameterIn.HEADER) @MbrId Long mbrId) {
        return SuccessResponse.success(SuccessCode.FIND_CART_SHARE_LIST_SUCCESS,
                cartShareService.findCartShareList(mbrId));
    }
}
