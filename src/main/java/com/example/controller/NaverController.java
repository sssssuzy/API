package com.example.controller;

import java.io.*;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.NaverAPI;
import com.example.domain.ShopVO;
import com.example.persistence.ShopDAO;

@Controller
public class NaverController {
	
	@RequestMapping(value="shop.json", 
		         method=RequestMethod.GET, 
		         produces="application/json;charset=UTF-8")
	@ResponseBody
	public String shop(String query) throws Exception{
		return NaverAPI.connection(query);
	}
	
	@RequestMapping("naver")
	public String naver(){
		return "naver";
	}
	
	@Autowired
	ShopDAO dao;
	
	@RequestMapping("insert")
	@ResponseBody
	public void insert(ShopVO vo){
		//파일 이름만 따오기
		  String file = vo.getImage().substring(vo.getImage().lastIndexOf('/') + 1);
	      int idxQuery=file.indexOf('?');
	      if(file.indexOf("?") > 0) file=file.substring(0,idxQuery); 
	      
	      String path="c:/zzz/image/";
	      File filePath=new File(path);
	      if(!(filePath).exists()) filePath.mkdir();
	      try {
	         URL url = new URL(vo.getImage());
	         InputStream in=url.openStream();
	         OutputStream out=new FileOutputStream(path + file);
	         FileCopyUtils.copy(in, out);
	      }catch(Exception e) { 
	         System.out.println(e.toString()); 
	      }
	      vo.setImage(file);
		dao.insert(vo);
	}
	
	@RequestMapping("read")
	@ResponseBody
	public int read(String pid){
		int result=0;
		ShopVO vo = dao.read(pid);
		if(vo!=null){
			result=1;
		}
		return result;
	}
	
}
