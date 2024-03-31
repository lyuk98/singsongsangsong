package com.ssafy.singsongsangsong.job;

import org.springframework.batch.item.ItemProcessor;

import com.ssafy.singsongsangsong.dto.PlayCountDto;
import com.ssafy.singsongsangsong.entity.Play;

public class PlayCountProcessor implements ItemProcessor<Play, PlayCountDto> {

	@Override
	public PlayCountDto process(Play play) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
