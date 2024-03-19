package com.ssafy.singsongsangsong.exception.artist;

public class AlreadyFollowException extends RuntimeException {
	public AlreadyFollowException(String msg) {
		super(msg);
	}

	public AlreadyFollowException() {
		super("이미 팔로우한 아티스트입니다.");
	}
}
