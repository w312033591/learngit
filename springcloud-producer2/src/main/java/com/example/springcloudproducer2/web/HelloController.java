package com.example.springcloudproducer2.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public  String hello (@RequestParam String name){
        return "hello"+name+",this is springcloud-producer2";
    }
}
