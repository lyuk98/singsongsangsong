package com.ssafy.singsongsangsong.constraints;

public enum EmotionConstraints {
	EMOTION1("moved"),
	EMOTION2("like"),
	EMOTION3("excited"),
	EMOTION4("energized"),
	EMOTION5("funny"),
	EMOTION6("sad");
	private final String name;

	EmotionConstraints(String emotionName) {
		this.name = emotionName;
	}

	public String getName() {
		return name;
	}
}
