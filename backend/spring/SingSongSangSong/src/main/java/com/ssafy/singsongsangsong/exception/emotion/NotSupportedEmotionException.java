package com.ssafy.singsongsangsong.exception.emotion;

public class NotSupportedEmotionException extends RuntimeException {
	public NotSupportedEmotionException(String msg) {
		super(msg);
	}

	public NotSupportedEmotionException() {
		super("지원하지 않는 감정입니다.");
	}
}
