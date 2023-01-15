package com.ssg.sausageorderapi.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    /**
     * 400 Bad Request
     */
    VALIDATION_EXCEPTION(ErrorStatusCode.BAD_REQUEST, "잘못된 요청입니다."),
    VALIDATION_ENUM_VALUE_EXCEPTION(ErrorStatusCode.BAD_REQUEST, "잘못된 Enum 값 입니다."),
    VALIDATION_REQUEST_MISSING_EXCEPTION(ErrorStatusCode.BAD_REQUEST, "필수적인 요청 값이 입력되지 않았습니다."),
    VALIDATION_WRONG_TYPE_EXCEPTION(ErrorStatusCode.BAD_REQUEST, "잘못된 타입이 입력되었습니다."),
    VALIDATION_CART_SHARE_ITEM_QTY_EXCEPTION(ErrorStatusCode.BAD_REQUEST, "공유장바구니상품 수량은 1보다 작을 수 없습니다."),
    VALIDATION_CART_SHARE_ITEM_COMM_EXCEPTION(ErrorStatusCode.BAD_REQUEST, "공유장바구니상품 공통 여부가 이미 요청한 상태입니다."),
    VALIDATION_CART_SHARE_MBR_PROG_EXCEPTION(ErrorStatusCode.BAD_REQUEST, "공유장바구니멤버 진행 상태가 이미 요청한 상태입니다."),

    /**
     * 401 UnAuthorized
     */
    UNAUTHORIZED_EXCEPTION(ErrorStatusCode.UNAUTHORIZED, "토큰이 만료되었습니다. 다시 로그인 해주세요."),

    /**
     * 403 Forbidden
     */
    FORBIDDEN_EXCEPTION(ErrorStatusCode.FORBIDDEN, "허용하지 않는 요청입니다."),
    FORBIDDEN_CART_SHARE_ACCESS_EXCEPTION(ErrorStatusCode.FORBIDDEN, "해당 장바구니에 접근 권한이 없습니다."),
    FORBIDDEN_CART_SHARE_ITEM_ACCESS_EXCEPTION(ErrorStatusCode.FORBIDDEN, "해당 장바구니상품에 접근 권한이 없습니다."),
    FORBIDDEN_CART_SHARE_MASTR_ACCESS_EXCEPTION(ErrorStatusCode.FORBIDDEN, "해당 장바구니의 마스터에게만 접근 권한이 있습니다."),

    /**
     * 404 Not Found
     */
    NOT_FOUND_EXCEPTION(ErrorStatusCode.NOT_FOUND, "존재하지 않습니다."),
    NOT_FOUND_CART_SHARE_EXCEPTION(ErrorStatusCode.NOT_FOUND, "존재하지 않는 공유장바구니입니다."),
    NOT_FOUND_CART_SHARE_ITEM_EXCEPTION(ErrorStatusCode.NOT_FOUND, "존재하지 않는 공유장바구니상품입니다."),
    NOT_FOUND_CART_SHARE_MBR_EXCEPTION(ErrorStatusCode.NOT_FOUND, "존재하지 않는 공유장바구니멤버입니다."),

    /**
     * 409 Conflict
     */
    CONFLICT_EXCEPTION(ErrorStatusCode.CONFLICT, "이미 존재합니다."),

    /**
     * 500 Internal Server Exception
     */
    INTERNAL_SERVER_EXCEPTION(ErrorStatusCode.INTERNAL_SERVER, "예상치 못한 서버 에러가 발생하였습니다."),

    /**
     * 502 Bad Gateway
     */
    BAD_GATEWAY_EXCEPTION(ErrorStatusCode.BAD_GATEWAY, "일시적인 에러가 발생하였습니다.\n잠시 후 다시 시도해주세요!"),

    /**
     * 503 Service UnAvailable
     */
    SERVICE_UNAVAILABLE_EXCEPTION(ErrorStatusCode.SERVICE_UNAVAILABLE, "현재 점검 중입니다.\n잠시 후 다시 시도해주세요!"),
    ;

    private final ErrorStatusCode statusCode;
    private final String message;

    public int getStatus() {
        return statusCode.getStatus();
    }
}
