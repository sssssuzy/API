package com.example.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.ShopVO;

@Repository
public class ShopDAOImpl implements ShopDAO{
	String namespace="com.example.mapper.ShopMapper";
	@Autowired
	SqlSession session;
	
	@Override
	public void insert(ShopVO vo) {
		session.insert(namespace+".insert",vo);		
	}	
	@Override
	public ShopVO read(String pid) {
		return session.selectOne(namespace+".read",pid);
	}	
}
