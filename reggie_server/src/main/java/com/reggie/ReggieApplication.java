package com.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.annotation.Validated;
//http://localhost:8081/#/login  管理系统前端
@ServletComponentScan //开启servlet组件支持
@SpringBootApplication // 整合MyBatis
@EnableTransactionManagement // 开启事务管理
@EnableCaching //开启注解缓存
@EnableScheduling//开启任务调度
@Slf4j
@Validated //配置类校验
public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class, args);
        log.info("server started");
    }
}
