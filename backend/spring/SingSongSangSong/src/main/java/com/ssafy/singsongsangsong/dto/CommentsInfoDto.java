package com.ssafy.singsongsangsong.dto;

import java.time.LocalDateTime;

import com.ssafy.singsongsangsong.entity.Comments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentsInfoDto {
	private Long artistId;
	private String nickname;
	private String content;
	private String profileImageFileName;
	private LocalDateTime createdAt;

	public static CommentsInfoDto from(Comments comments) {
		return CommentsInfoDto.builder()
			.artistId(comments.getArtist().getId())
			.nickname(comments.getArtist().getNickname())
			.content(comments.getContent())
			.profileImageFileName(comments.getArtist().getProfileImage().getOriginalFileName())
			.createdAt(comments.getCreatedDate())
			.build();
	}
}
