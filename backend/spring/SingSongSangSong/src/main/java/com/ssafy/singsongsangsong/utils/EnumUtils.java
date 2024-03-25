package com.ssafy.singsongsangsong.utils;

import com.ssafy.singsongsangsong.constants.EmotionConstants;
import com.ssafy.singsongsangsong.exception.emotion.NotSupportedEmotionException;

public class EnumUtils {

	public static class Emotion {
		public static boolean checkIfSupported(String emotion) throws NotSupportedEmotionException {
			for (EmotionConstants e : EmotionConstants.values()) {
				if (e.getName().equals(emotion)) {
					return true;
				}
			}
			throw new NotSupportedEmotionException("emotion name을 확인해주세요.");
		}
	}
}
