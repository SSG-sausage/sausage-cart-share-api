package com.ssg.sausageorderapi.example.repository;

import com.ssg.sausageorderapi.example.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example, Long>, ExampleRepositoryCustom {

}
