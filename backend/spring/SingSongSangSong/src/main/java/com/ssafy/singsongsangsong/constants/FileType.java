package com.ssafy.singsongsangsong.constants;

public enum FileType {
	IMAGE("image"),
	AUDIO("audio");

	private String name;

	FileType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
