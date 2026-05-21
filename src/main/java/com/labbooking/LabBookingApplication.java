package com.labbooking;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.labbooking.mapper")
public class LabBookingApplication {
    public static void main(String[] args) {
        SpringApplication.run(LabBookingApplication.class, args);
    }
}