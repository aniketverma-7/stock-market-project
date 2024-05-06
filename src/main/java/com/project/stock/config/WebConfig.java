package com.project.stock.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Add JwtInterceptor to intercept all requests
        registry.addInterceptor(jwtInterceptor).excludePathPatterns("/auth/**");
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/user/**");
    }

}
