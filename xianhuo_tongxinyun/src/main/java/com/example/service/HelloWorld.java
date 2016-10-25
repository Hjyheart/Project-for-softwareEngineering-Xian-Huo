package com.example.service;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by deado on 2016/10/22.
 */

@RequestMapping("/")
public class HelloWorld {
    @RequestMapping("/HelloWorld")
    public String HelloWorld(){
        return "Hello World";
    }
}
