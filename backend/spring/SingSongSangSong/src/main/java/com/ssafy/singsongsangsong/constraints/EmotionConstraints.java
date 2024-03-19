package com.ssafy.singsongsangsong.constraints;

public enum EmotionConstraints {
	EMOTION1("emotion1"),
	EMOTION2("emotion2"),
	EMOTION3("emotion3"),
	EMOTION4("emotion4"),
	EMOTION5("emotion5"),
	EMOTION6("emotion6");
	private final String name;

	EmotionConstraints(String emotionName) {
		this.name = emotionName;
	}

	public String getName() {
		return name;
	}
}
