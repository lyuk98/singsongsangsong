package com.ssafy.singsongsangsong.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fileName;
	private String originalFileName;

	private Long ownerId;

	public static File of(String fileName, String originalFileName, Long ownerId) {
		File file = new File();
		file.fileName = fileName;
		file.originalFileName = originalFileName;
		file.ownerId = ownerId;
		return file;
	}
}
