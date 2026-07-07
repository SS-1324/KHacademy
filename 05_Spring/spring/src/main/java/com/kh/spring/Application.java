package com.kh.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
* @SpringBootApplication 해당 어노테이션은 3개의 어노테이션을 합처놓은 것
	- @Configuration 			: 이 클래스는 설정 클래스다.
	- @EnableAutoConfiguration  : Spring Boot의 자동 설정 기능을 활성화 하겠다.
	- @ComponentScan			: 이 클래스가 위치한 패키지(및 하위 패키지)에서 @Component, @RestController등을
	* 							  자동으로 찾아서 Bean으로 등록
* */

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
