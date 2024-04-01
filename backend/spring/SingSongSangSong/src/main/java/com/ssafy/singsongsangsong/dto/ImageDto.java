package com.ssafy.singsongsangsong.dto;

import com.ssafy.singsongsangsong.entity.File;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ImageDto {
	private String savedFileName;
	private String originalFileName;

	public static String DEFAULT_SAVED_FILENAME = "default.jpg";
	public static String DEFAULT_ORIGINAL_FILENAME = "default.jpg";

	public static ImageDto from(File image) {
		String originalImageName;
		String savedImageName;

		if (image != null) {
			originalImageName = image.getOriginalFileName();
			savedImageName = image.getSavedFileName();
		} else {
			originalImageName = DEFAULT_ORIGINAL_FILENAME;
			savedImageName = DEFAULT_SAVED_FILENAME;
		}

		return ImageDto.builder()
			.originalFileName(originalImageName)
			.savedFileName(savedImageName)
			.build();
	}
}
