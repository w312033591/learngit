package com.example.springcloudconfigclient.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public  class TestController {

        @Value("${from}")
        private String from;

        @GetMapping("/from")
        public String from() {

                return this.from;
        }

        public void setFrom(String from) {
                this.from = from;
        }

        public String getFrom() {
                return from;
        }

}