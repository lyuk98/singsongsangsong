package com.ssafy.singsongsangsong.constants;

public enum DefaultFileName {
	DEFAULT_ALBUM_PICTURE("default_album"),
	DEFAULT_PROFILE_PICTURE("default");

	private final String name;

	DefaultFileName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
