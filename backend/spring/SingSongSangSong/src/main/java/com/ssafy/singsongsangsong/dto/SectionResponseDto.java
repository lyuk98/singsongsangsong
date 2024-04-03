package com.ssafy.singsongsangsong.dto;

import com.ssafy.singsongsangsong.entity.Structure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectionResponseDto {
	private String label;
	private int start;
	private int end;

	public static SectionResponseDto from(Structure structure) {
		SectionResponseDto sectionResponseDto = new SectionResponseDto();
		sectionResponseDto.setLabel(structure.getLabel());
		sectionResponseDto.setStart(structure.getStartTime());
		sectionResponseDto.setEnd(structure.getEndTime());

		return sectionResponseDto;
	}
}
