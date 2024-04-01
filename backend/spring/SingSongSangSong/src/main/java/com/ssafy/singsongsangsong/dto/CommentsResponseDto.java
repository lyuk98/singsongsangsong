package com.ssafy.singsongsangsong.dto;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ssafy.singsongsangsong.entity.Comments;
import com.ssafy.singsongsangsong.entity.File;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentsResponseDto {

	List<CommentsResponse> comments;

	@Builder
	public static class CommentsResponse {
		private Long authorId;
		private String artistNickname;
		private String content;
		private String imageFileName;
		private Date createdAt;

		public static CommentsResponse from(Comments comments) {
			Date date = Date.from(comments.getCreatedDate().atZone(ZoneId.systemDefault()).toInstant());
			Optional<File> profileImage = Optional.ofNullable(comments.getArtist().getProfileImage());
			return CommentsResponse.builder()
				.authorId(comments.getArtist().getId())
				.artistNickname(comments.getArtist().getNickname())
				.content(comments.getContent())
				.imageFileName(
					profileImage.orElseGet(() ->
							new File(999L, "default.jpg", "default.jpg", comments.getArtist().getId()))
						.getOriginalFileName())
				.createdAt(date)
				.build();
		}

	}

}
