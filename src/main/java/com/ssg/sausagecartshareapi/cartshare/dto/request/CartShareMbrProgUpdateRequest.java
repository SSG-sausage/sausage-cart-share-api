package com.ssg.sausagecartshareapi.cartshare.dto.request;

import com.ssg.sausagecartshareapi.cartshare.entity.ProgStatCd;
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
public class CartShareMbrProgUpdateRequest {

    @NotNull(message = "progStatCd를 입력해주세요.")
    @Schema(name = "progStatCd", requiredMode = RequiredMode.REQUIRED, allowableValues = {"IN_PROGRESS, DONE"})
    private ProgStatCd progStatCd;
}
