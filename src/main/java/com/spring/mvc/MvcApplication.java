package com.spring.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.spring.mvc") // 서블릿이 있는 위치를 스프링에 알려주는 것.
public class MvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcApplication.class, args); // 톰캣 활성화
	}

	@Bean
	public InternalResourceViewResolver resolver(){	// 리졸버 설정. 컨트롤러의 리퀘스트매핑 함수의 리턴값에 앞 뒤에 붙일 스트링을 설정.
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

}
