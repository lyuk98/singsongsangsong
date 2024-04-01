package com.ssafy.singsongsangsong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.singsongsangsong.common.converter.EmotionTypeMessageConverter;
import com.ssafy.singsongsangsong.common.converter.FileTypeMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new EmotionTypeMessageConverter());
		registry.addConverter(new FileTypeMessageConverter());
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:3000", "https://api.singsongsangsong.com",
				"https://www.singsongsangsong.com")
			.allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")
			.allowedHeaders("*")
			.allowCredentials(true)
			.maxAge(3000);
	}

}