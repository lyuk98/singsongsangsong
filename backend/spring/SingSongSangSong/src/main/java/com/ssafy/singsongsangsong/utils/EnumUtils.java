package com.ssafy.singsongsangsong.utils;

import com.ssafy.singsongsangsong.constants.EmotionsConstants;
import com.ssafy.singsongsangsong.exception.emotion.NotSupportedEmotionException;

public class EnumUtils {

	public static class Emotion {
		public static boolean checkIfSupported(java.lang.String emotion) throws NotSupportedEmotionException {
			for (EmotionsConstants e : EmotionsConstants.values()) {
				if (e.getName().equals(emotion)) {
					return true;
				}
			}
			throw new NotSupportedEmotionException("emotion name을 확인해주세요.");
		}
	}
}
