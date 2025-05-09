package com.Ntra.PROGIGS.Configration;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProjectConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")

                        .allowedOrigins("http://192.168.31.177:5174")
                        .allowedOrigins("http:// 172.20.10.4:5173") // Change IP only here

                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");

            }
        };
    }

    @Bean
    public Cloudinary getCloudinary() {
        Map config = new HashMap();
        config.put("cloud_name", "dcnlmcvrr");
        config.put("api_key", "264847986489812");
        config.put("api_secret", "b5s_kkTn2C0XeD1eli3D92-R-Xw");
        config.put("secure", true);
        return new Cloudinary(config);
    }
}
