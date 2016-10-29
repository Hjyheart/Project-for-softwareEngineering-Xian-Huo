package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by deado on 2016/10/23.
 */
@RestController
@RequestMapping("/Test")
public class TestController {
    @RequestMapping("/Hello")
    public String Hello(){
        return "Hello";
    }
}
