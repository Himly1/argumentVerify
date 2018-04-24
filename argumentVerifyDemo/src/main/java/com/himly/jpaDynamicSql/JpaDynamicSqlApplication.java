package com.himly.jpaDynamicSql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.constraints.NotBlank;

@SpringBootApplication
public class JpaDynamicSqlApplication {


	public static void main(String[] args) {
		SpringApplication.run(JpaDynamicSqlApplication.class, args);
		test(null);
	}


	public static void test(@NotBlank(message = "名称不能为空") String name) {

	}
}
