package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.example.demo.servlet.SimpleServlet;

@SpringBootApplication
@EnableTransactionManagement
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean<SimpleServlet> simpleServletRegistration() {
        ServletRegistrationBean<SimpleServlet> registration = new ServletRegistrationBean<>();
        registration.setServlet(new SimpleServlet());
        registration.addUrlMappings("/simple-servlet");
        return registration;
    }
}
