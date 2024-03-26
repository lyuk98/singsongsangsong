package com.ssafy.singsongsangsong.repository.maria.song;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.singsongsangsong.util.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> , GenreRepositoryCustom{
}
