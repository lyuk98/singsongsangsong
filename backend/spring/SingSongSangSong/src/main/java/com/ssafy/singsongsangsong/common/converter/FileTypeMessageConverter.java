package com.ssafy.singsongsangsong.common.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ssafy.singsongsangsong.constants.FileType;

@Component
public class FileTypeMessageConverter implements Converter<String, FileType> {

	@Override
	public FileType convert(String source) {
		return FileType.valueOf(source);
	}
}
