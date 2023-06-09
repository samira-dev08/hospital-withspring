package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        /*BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        System.out.println( passwordEncoder.encode("12345"));
        System.out.println( passwordEncoder.encode("1234"));
*/

        SpringApplication.run(Main.class, args);

    }
}
