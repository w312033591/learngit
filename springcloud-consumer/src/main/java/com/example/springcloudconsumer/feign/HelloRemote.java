package com.example.springcloudconsumer.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author yongzhen [myz@6294369664myz@163.com]
 * @Date 2017/9/14 16:41
 * feign调用接口
 */
@FeignClient(name = "spring-cloud-producer1")
public interface HelloRemote {
   @GetMapping(value = "/hello")
   String hello(@RequestParam(value ="name") String name );
}
