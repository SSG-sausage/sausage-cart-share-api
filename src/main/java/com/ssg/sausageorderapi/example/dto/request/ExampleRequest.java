package com.ssg.sausageorderapi.example.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExampleRequest {

    @Schema(name = "제목", requiredMode = RequiredMode.REQUIRED)
    private String title;

    @Schema(name = "내용")
    private String content;
}
