package com.datacenter.eud.course.course_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;

@Configuration
public class AppConfiguration {
	
	@Bean
	DozerBeanMapper getMapper() {
		DozerBeanMapperBuilder builder = DozerBeanMapperBuilder.create();
		return (DozerBeanMapper) builder.build();
	}
	
}
