package com.example.spring_test.spring_test.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_test.spring_test.entity.MyResource;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Another Test";
    }

    @GetMapping("/hello")
    public Link hello() {
        Link link = Link.of("hello world!s");
        return link;
    }

    @GetMapping("/login")
    public String login(){
        return "Login Form";
    }

    @GetMapping("/resource")
    public EntityModel<MyResource> getResource() {
        MyResource myResource = new MyResource("Example Resource");
        Link link = Link.of("/api/resource");
        return EntityModel.of(myResource, link);
    }
}
