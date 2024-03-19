package com.ssafy.singsongsangsong.common.advice;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.singsongsangsong.common.ErrorEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

	public ErrorEntity handleException(Exception e) {
		log.error(e.getMessage(), e);
		return ErrorEntity.builder().code("500").message("서버 내부 오류").build();
	}
}
