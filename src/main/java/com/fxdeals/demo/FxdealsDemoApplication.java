package com.fxdeals.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = { //
        "com.fxdeals.demo", //
})
@EnableSwagger2
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class FxdealsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FxdealsDemoApplication.class, args);
		
		
	}

}
