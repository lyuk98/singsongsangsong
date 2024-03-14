package com.ssafy.singsongsangsong.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Similarities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fromSongId")
	private Song fromSong;

	@ManyToOne
	@JoinColumn(name = "toSongId")
	private Song toSong;

	private double similarityPercent;
	private int partStartTime;
	private int partEndTime;
}
