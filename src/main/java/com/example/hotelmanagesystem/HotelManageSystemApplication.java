package com.example.hotelmanagesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class HotelManageSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelManageSystemApplication.class, args);
    }

}
