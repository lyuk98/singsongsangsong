package com.ssafy.singsongsangsong.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Deprecated(forRemoval = true, since = "python server에서 실시간 처리가 가능할 정도의 퍼포먼스가 나옴")
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
	private int fromPartStartTime;
	private int fromPartEndTime;
	private int toPartStartTime;
	private int toPartEndTime;
}
