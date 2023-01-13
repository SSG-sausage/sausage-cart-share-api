package com.ssg.sausageorderapi.cartshare.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartShareItemSaveRequest {

    @NotNull(message = "itemId를 입력해주세요.")
    @Schema(name = "itemId", requiredMode = RequiredMode.REQUIRED)
    private Long itemId;

    @NotNull(message = "itemQty를 입력해주세요.")
    @Schema(name = "itemQty", requiredMode = RequiredMode.REQUIRED)
    private int itemQty;
}
