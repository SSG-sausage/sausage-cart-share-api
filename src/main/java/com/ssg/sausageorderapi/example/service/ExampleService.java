package com.ssg.sausageorderapi.example.service;

import com.ssg.sausageorderapi.example.dto.request.ExampleRequest;
import com.ssg.sausageorderapi.example.dto.response.ExampleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleService {

    public ExampleResponse create(ExampleRequest request) {
        return ExampleResponse.of();
    }
}
