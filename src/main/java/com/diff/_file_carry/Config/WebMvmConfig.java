package com.diff._file_carry.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvmConfig implements WebMvcConfigurer {
	//외부에서 Back접근
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/back/**")
        		.allowedOrigins("*")
                .allowedMethods("GET", "POST");
    }
}
