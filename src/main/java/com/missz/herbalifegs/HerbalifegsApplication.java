package com.missz.herbalifegs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.missz.herbalifegs.mapper")
@SpringBootApplication
public class HerbalifegsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HerbalifegsApplication.class, args);
    }

}
