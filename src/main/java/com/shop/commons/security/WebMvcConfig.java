package com.shop.commons.security;

import com.shop.commons.interceptor.AdminViewInterceptor;
import com.shop.commons.interceptor.ServiceViewInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final ServiceViewInterceptor serviceViewInterceptor;
    private final AdminViewInterceptor adminViewInterceptor;
    @Value("${upload_path}")
    private String uploadPath;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serviceViewInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/img/**", "/css/**", "/images/**", "/js/**", "/api/**", "/admin/**");
        registry.addInterceptor(adminViewInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/img/**", "/admin/css/**", "/images/**", "/admin/js/**", "/api/admin/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadPath);
    }

}