package com.douzone.jblog.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.douzone.config.web.FileUploadConfig;
import com.douzone.config.web.MvcConfig;
import com.douzone.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy //자동스캐닝 가능하게
@ComponentScan({"com.douzone.jblog.controller", "com.douzone.jblog.exception"})
@Import({MvcConfig.class, SecurityConfig.class, FileUploadConfig.class})
public class WebConfig implements WebMvcConfigurer{

	//Site Interceptor 보안과 관련없어서 전역설정에다가 하는거 사실상 여기가 전역 ㅇㅇ 다 담는 통이니깐 
	
	
}
