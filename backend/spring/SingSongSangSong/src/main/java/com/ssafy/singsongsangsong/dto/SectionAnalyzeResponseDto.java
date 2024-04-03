package com.ssafy.singsongsangsong.dto;

import java.awt.*;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectionAnalyzeResponseDto {
	private List<SectionElementDto> sectionElementList;
	private ImageDto mfccImage;

	public static SectionAnalyzeResponseDto from(List<SectionElementDto> list, ImageDto mfcc) {
		SectionAnalyzeResponseDto dto = new SectionAnalyzeResponseDto();
		dto.setSectionElementList(list);
		dto.setMfccImage(mfcc);
		return dto;
	}
}
