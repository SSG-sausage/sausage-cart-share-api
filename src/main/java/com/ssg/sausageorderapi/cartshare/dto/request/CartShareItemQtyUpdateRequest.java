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
public class CartShareItemQtyUpdateRequest {

    @NotNull(message = "qty를 입력해주세요.")
    @Schema(name = "qty", requiredMode = RequiredMode.REQUIRED, allowableValues = {"-1", "1"})
    private int qty;
}
