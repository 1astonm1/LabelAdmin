package com.astonm.labelAdmin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author astonm
 * @date 2021/7/3
 * @description:
 **/

@Api(tags = "Hello后台")
@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    @ApiOperation(value = "hello接口", httpMethod = "GET")
    public void hello(){
        log.error("hello!");
        System.out.println("hello!");
    }

    @GetMapping("/getId")
    @ApiOperation(value = "获取id接口", httpMethod = "GET")
    public void getId(@RequestParam("id") int id){
        System.out.println("你的id是：" + id);
    }

}
