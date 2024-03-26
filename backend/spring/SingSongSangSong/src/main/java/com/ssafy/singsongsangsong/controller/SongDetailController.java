package com.ssafy.singsongsangsong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.singsongsangsong.dto.SongDetailResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/song")
public class SongDetailController {
	// @GetMapping("/{id}")
	// public SongDetailResponseDto songDetail(@PathVariable Long id) {
	//
	// }
}
