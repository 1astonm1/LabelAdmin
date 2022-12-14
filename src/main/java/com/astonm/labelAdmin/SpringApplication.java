package com.astonm.labelAdmin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author astonm
 * @date 2021/12/24
 */
@SpringBootApplication(scanBasePackages = "com")
@MapperScan("com.astonm.labelAdmin.dao.mapper")
@Slf4j
public class SpringApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
        log.info("labelAdmin start successful ...   ");
    }
}
