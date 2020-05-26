package com.zt.gp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zt.gp.mapper")
public class GpApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpApplication.class, args);
    }

}
