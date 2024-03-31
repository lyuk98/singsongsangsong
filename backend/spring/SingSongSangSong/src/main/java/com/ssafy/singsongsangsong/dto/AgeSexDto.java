package com.ssafy.singsongsangsong.dto;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgeSexDto {
	
	private int age;
	private char sex;
	
	@Override
	public int hashCode() {
		return Objects.hash(age, sex);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgeSexDto other = (AgeSexDto) obj;
		return age == other.age && sex == other.sex;
	}

}
