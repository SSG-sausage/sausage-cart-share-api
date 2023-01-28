package com.ssg.sausagecartshareapi.cartshare.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class MbrUpdateDto {

    private Long mbrId;
    private String message;

    public static MbrUpdateDto of(Long mbrId, String message) {
        return MbrUpdateDto.builder()
                .mbrId(mbrId)
                .message(message)
                .build();
    }
}
