package com.ssafy.singsongsangsong.entity;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Song extends BaseTimeEntity {
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

	@OneToMany(mappedBy = "song")
	private List<Atmosphere> atmospheres;

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

	private boolean isPublished;
	private String musicLocation;

	private int movedEmotionCount;
	private int likeEmotionCount;
	private int excitedEmotionCount;
	private int cheerUpEmotionCount;
	private int funnyEmotionCount;
	private int sadEmotionFCount;
}
