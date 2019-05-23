package com.example.demo;

import javax.servlet.MultipartConfigElement;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.example.demo.mapper")
@EnableTransactionManagement
public class Demo0129Application extends SpringBootServletInitializer implements WebApplicationInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Demo0129Application.class, args);
	}
	
	
	
	@Bean  
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        //单个文件最大  
        factory.setMaxFileSize("102400KB"); //KB,MB  
        /// 设置总上传数据总大小  
        factory.setMaxRequestSize("102400KB");  
        return factory.createMultipartConfig();
    }  
}



