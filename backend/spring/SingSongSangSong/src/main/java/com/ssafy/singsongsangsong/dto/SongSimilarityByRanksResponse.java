package com.ssafy.singsongsangsong.dto;

import java.time.LocalDateTime;
import java.util.List;

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
public class SongSimilarityByRanksResponse {

	private int size;

	private String albumImageFileName;
	private String title;
	private LocalDateTime createdDate;

	List<Comparison> comparison;

	@Builder
	public static class Comparison {
		private Float correlation;
		private Target target;

		@Builder
		public static class Target {
			private Long songId;
			private String albumImageFileName;
			private String title;
			private LocalDateTime createdDate;
		}
	}

}
