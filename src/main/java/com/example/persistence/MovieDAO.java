package com.example.persistence;

import com.example.domain.MovieVO;

public interface MovieDAO {
	public void insert(MovieVO vo);
	public MovieVO read(String wdate,String rank);
}
