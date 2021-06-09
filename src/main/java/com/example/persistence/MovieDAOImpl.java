package com.example.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.MovieVO;

@Repository
public class MovieDAOImpl implements MovieDAO {
	String namespace="com.example.mapper.MovieMapper";
			
	@Autowired
	SqlSession session;

	@Override
	public void insert(MovieVO vo) {
		session.insert(namespace+".insert",vo);
		
	}

	@Override
	public MovieVO read(String wdate,String rank) {
		return null;
	}
	
	
}
