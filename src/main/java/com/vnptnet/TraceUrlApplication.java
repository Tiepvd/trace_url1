package com.vnptnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("/integration/tailfile.xml")
public class TraceUrlApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TraceUrlApplication.class, args);
    }
}
