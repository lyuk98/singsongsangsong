package com.ssafy.singsongsangsong.dto;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.singsongsangsong.entity.Image;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuestJoinRequestDto {
	@NotBlank
	private String nickname;
	private int age;
	private String introduction;
	private MultipartFile profileImage;
	private char sex;
}
