package com.practice.board.redistest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class RedisController {

    @RequestMapping("test")
    public String test(HttpSession session){
        session.setAttribute("email", "test");
        return "test";
    }
}
