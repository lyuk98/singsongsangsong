package com.ssafy.singsongsangsong.common.advice;

import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.ssafy.singsongsangsong.common.ErrorEntity;
import com.ssafy.singsongsangsong.common.ResponseUtil;

@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
		Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
		ServerHttpResponse response) {
		if (body instanceof ErrorEntity) {
			return ResponseUtil.error((ErrorEntity)body);
		} else if (body instanceof Resource) {
			return body;
		} else {
			return ResponseUtil.ok(body);
		}
	}
}
