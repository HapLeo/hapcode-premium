package com.github.hapcode.cml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.github.hapcode"})
public class HapcodeCmlApplication {

    public static void main(String[] args) {
        SpringApplication.run(HapcodeCmlApplication.class, args);
    }

}
