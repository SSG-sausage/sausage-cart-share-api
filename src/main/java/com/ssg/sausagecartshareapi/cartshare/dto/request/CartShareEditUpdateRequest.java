package com.ssg.sausagecartshareapi.cartshare.dto.request;

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
public class CartShareEditUpdateRequest {

    @NotNull(message = "editPsblYn를 입력해주세요.")
    @Schema(name = "editPsblYn", requiredMode = RequiredMode.REQUIRED)
    private boolean editPsblYn;
}
