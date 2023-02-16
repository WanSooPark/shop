package com.allddaom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class AllDdaomApplication {

    public static void main(String[] args) {
        SpringApplication.run(AllDdaomApplication.class, args);
    }

    @PostConstruct
    public void onPostConstruct() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

}
