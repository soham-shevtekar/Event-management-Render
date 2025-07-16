package com.example.demo.config; // Adjust this to your package structure

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry) { // Removed @Override
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}
