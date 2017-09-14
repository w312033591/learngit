package com.example.springcloudconsumer.web;

import com.example.springcloudconsumer.feign.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yongzhen [myz@6294369664myz@163.com]
 * @Date 2017/9/14 16:54
 */
@RestController
public class ConsumerController {

        @Autowired
        HelloRemote helloRemote;

        @GetMapping("/hello/{name}")
        public  String hello(@PathVariable("name") String name){
             return  helloRemote.hello(name);
        }
}
