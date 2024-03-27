package com.ssafy.singsongsangsong.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "artistId")
	private Artist artist;
	@OneToOne
	@JoinColumn(name = "mfccImageId")
	private Image mfccImage;
	@OneToOne
	@JoinColumn(name = "spectrumImageId")
	private Image spectrumImage;
	@OneToOne
	@JoinColumn(name = "albumImageId")
	private Image albumImage;

	private String title;
	private String lyrics;
	private String customGenre;
	private String chord;
	private int bpm;
	private int duration;

	private int playCount;
	private int downloadCount;
	private int likeCount;

	private int weeklyPlayCount;
	private int weeklyDownloadCount;
	private int weeklyLikeCount;

	@ColumnDefault("false")
	private boolean isPublished;

	private String musicLocation;

	@ColumnDefault("0")
	private int movedEmotionCount;
	@ColumnDefault("0")
	private int likeEmotionCount;
	@ColumnDefault("0")
	private int excitedEmotionCount;

	@ColumnDefault("0")
	private int energizedEmotionCount;
	@ColumnDefault("0")
	private int funnyEmotionCount;
	@ColumnDefault("0")
	private int sadEmotionCount;

	@ColumnDefault("false")
	private boolean isAnalyzed;
}
