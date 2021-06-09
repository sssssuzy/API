package com.example.persistence;

import com.example.domain.ShopVO;

public interface ShopDAO {
	public void insert(ShopVO vo);	
	public ShopVO read(String pid);
}
