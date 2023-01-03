package com.ssg.sausageorderapi.example.dto.response;

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
public class ExampleResponse {

    private String title;

    private String content;

    public static ExampleResponse of() {
        return ExampleResponse.builder()
                .title("title")
                .content("content")
                .build();
    }
}
