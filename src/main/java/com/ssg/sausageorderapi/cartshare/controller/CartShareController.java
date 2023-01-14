package com.ssg.sausageorderapi.cartshare.controller;

import com.ssg.sausageorderapi.cartshare.dto.request.CartShareItemQtyUpdateRequest;
import com.ssg.sausageorderapi.cartshare.dto.request.CartShareItemSaveRequest;
import com.ssg.sausageorderapi.cartshare.dto.response.CartShareFindListResponse;
import com.ssg.sausageorderapi.cartshare.dto.response.CartShareFindResponse;
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
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Operation(summary = "단일 장바구니 조회", responses = {
            @ApiResponse(responseCode = "200", description = "단일 장바구니 조회 성공입니다."),
            @ApiResponse(responseCode = "403", description = "해당 장바구니에 접근 권한이 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 공유장바구니입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러가 발생하였습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/cart-share/{cartShareId}")
    public ResponseEntity<SuccessResponse<CartShareFindResponse>> findCartShare(
            @PathVariable Long cartShareId,
            @Parameter(in = ParameterIn.HEADER) @MbrId Long mbrId) {
        return SuccessResponse.success(SuccessCode.FIND_CART_SHARE_SUCCESS,
                cartShareService.findCartShare(cartShareId, mbrId));
    }

    @Operation(summary = "장바구니에 상품 추가", responses = {
            @ApiResponse(responseCode = "200", description = "성공입니다."),
            @ApiResponse(responseCode = "400", description = "1. itemId를 입력해주세요. (itemId)\n2. itemQty를 입력해주세요. (itemQty)", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "해당 장바구니에 접근 권한이 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 공유장바구니입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러가 발생하였습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping("/cart-share/{cartShareId}/cart-share-item")
    public ResponseEntity<SuccessResponse<String>> saveCartShareItem(
            @PathVariable Long cartShareId,
            @Parameter(in = ParameterIn.HEADER) @MbrId Long mbrId,
            @Valid @RequestBody CartShareItemSaveRequest request) {
        cartShareService.saveCartShareItem(cartShareId, mbrId, request);
        return SuccessResponse.OK;
    }

    @Operation(summary = "장바구니의 상품 삭제", responses = {
            @ApiResponse(responseCode = "200", description = "성공입니다."),
            @ApiResponse(responseCode = "403", description = "1. 해당 장바구니에 접근 권한이 없습니다.\n2. 해당 장바구니상품에 접근 권한이 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "1. 존재하지 않는 공유장바구니입니다.\n2. 존재하지 않는 공유장바구니상품입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러가 발생하였습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @DeleteMapping("/cart-share/{cartShareId}/cart-share-item/{cartShareItemId}")
    public ResponseEntity<SuccessResponse<String>> saveCartShareItem(
            @PathVariable Long cartShareId,
            @PathVariable Long cartShareItemId,
            @Parameter(in = ParameterIn.HEADER) @MbrId Long mbrId) {
        cartShareService.deleteCartShareItem(cartShareId, cartShareItemId, mbrId);
        return SuccessResponse.OK;
    }

    @Operation(summary = "장바구니의 상품 수량 변경", responses = {
            @ApiResponse(responseCode = "200", description = "성공입니다."),
            @ApiResponse(responseCode = "400", description = "1. qty를 입력해주세요. (qty)\n2. 공유장바구니상품 수량은 1보다 작을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "1. 해당 장바구니에 접근 권한이 없습니다.\n2. 해당 장바구니상품에 접근 권한이 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "1. 존재하지 않는 공유장바구니입니다.\n2. 존재하지 않는 공유장바구니상품입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러가 발생하였습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PatchMapping("/cart-share/{cartShareId}/cart-share-item/{cartShareItemId}/qty")
    public ResponseEntity<SuccessResponse<String>> updateCartShareItemQty(
            @PathVariable Long cartShareId,
            @PathVariable Long cartShareItemId,
            @Parameter(in = ParameterIn.HEADER) @MbrId Long mbrId,
            @Valid @RequestBody CartShareItemQtyUpdateRequest request) {
        cartShareService.updateCartShareItemQty(cartShareId, cartShareItemId, mbrId, request);
        return SuccessResponse.OK;
    }
}
