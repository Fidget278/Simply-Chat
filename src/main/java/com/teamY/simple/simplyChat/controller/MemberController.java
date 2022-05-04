package com.teamY.simple.simplyChat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("hello")
    public String hello() {
        return "hello.html";
    }
}
