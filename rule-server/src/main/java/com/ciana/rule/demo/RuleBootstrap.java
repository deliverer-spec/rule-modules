package com.ciana.rule.demo;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.spring4all.swagger.EnableSwagger2Doc;

/**
 * 启动类
 *
 * @author yinbo
 * @create 2017-05-25 12:44
 */
@SpringBootApplication
@EnableSwagger2Doc
public class RuleBootstrap {
	public static void main(String[] args) {
		new SpringApplicationBuilder(RuleBootstrap.class).run(args);
	}
}
