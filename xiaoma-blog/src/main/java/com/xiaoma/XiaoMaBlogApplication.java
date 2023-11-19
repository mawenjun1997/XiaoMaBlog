package com.xiaoma;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaoma.mapper")
public class XiaoMaBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(XiaoMaBlogApplication.class, args);
    }
}
