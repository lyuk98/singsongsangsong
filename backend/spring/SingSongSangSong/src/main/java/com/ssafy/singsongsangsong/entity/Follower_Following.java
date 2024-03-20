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
public class Follower_Following extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fromArtistId")
	private Artist from;

	@ManyToOne
	@JoinColumn(name = "toArtistId")
	private Artist to;

	private Follower_Following(Artist from, Artist to) {
		this.from = from;
		this.to = to;
	}

	public static Follower_Following of(Artist follwer, Artist following) {
		return new Follower_Following(follwer, following);
	}
}
