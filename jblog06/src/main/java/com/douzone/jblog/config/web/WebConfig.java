package com.douzone.jblog.config.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.douzone.config.web.FileUploadConfig;
import com.douzone.config.web.MvcConfig;
import com.douzone.config.web.SecurityConfig;
import com.douzone.jblog.security.AuthUserHandlerMethodArgumentResolver;
import com.douzone.jblog.security.LoginInterceptor;
import com.douzone.jblog.security.LogoutInterceptor;

@SpringBootConfiguration
public class WebConfig implements WebMvcConfigurer{


	// Security Interceptors
	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

	@Bean
	public HandlerInterceptor logoutInterceptor() {
		return new LogoutInterceptor();
	}
	
	//<!-- Argument Resolver -->
	@Bean
	public HandlerMethodArgumentResolver handlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/user/auth");
		registry.addInterceptor(logoutInterceptor()).addPathPatterns("/user/logout");
	}
	
	
	@Override // 오버라이드라 내가 만들 수 있는 대상이 아니라서 메소드 호출하는 방식으로 ㄱㄱ 
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(handlerMethodArgumentResolver());   // 내가등록했던 아규먼트 추가
	}
	

}
