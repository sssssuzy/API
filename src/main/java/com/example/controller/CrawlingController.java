package com.example.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.MovieVO;
import com.example.persistence.MovieDAO;

@Controller
public class CrawlingController {
	
	@RequestMapping("more.json")
	@ResponseBody
	public List<Map<String,Object>> moreJson(){
		List<Map<String,Object>> array = new ArrayList<>();
		System.setProperty("webdriver.chrome.driver", "c:/temp/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.cgv.co.kr/movies/");
		
		WebElement contents = driver.findElement(By.id("contents"));
		WebElement more = contents.findElement(By.className("link-more"));
		more.click();
		
		//1초동안 기다린다(시스템 잠시 중지)
		try{
			Thread.sleep(1000);
		}catch(Exception e){}
		
		List<WebElement> elements = driver.findElements(By.cssSelector(".sect-movie-chart ol li"));
		System.out.println("데이터갯수:"+elements.size());
		
		for(WebElement e:elements){
			Map<String,Object> map = new HashMap<>();
			String title = e.findElement(By.className("title")).getText();
			String image = e.findElement(By.tagName("img")).getAttribute("src");
			String date = e.findElement(By.className("txt-info")).getText();
			map.put("title", title);
			map.put("image", image);
			map.put("date", date);
			array.add(map);
			System.out.println("제목:"+title);
		}
		driver.quit();
		return array;
	}
	
	@RequestMapping("movie.json")
	@ResponseBody
	public List<HashMap<String,Object>> moviejson(){
		List<HashMap<String,Object>> array = new ArrayList<>();
		try{
			Document doc = Jsoup.connect("http://www.cgv.co.kr/movies/").get();
			Elements elements = doc.select(".sect-movie-chart ol");
			for(Element e:elements.select("li")){
				HashMap map = new HashMap<String,Object>();
				String title=e.select(".title").text();
				String image=e.select("img").attr("src");
				String rank=e.select(".rank").text();
				String openDate=e.select(".txt-info").text();
				//System.out.println("개봉일" + openDate);
				if(!title.equals("")){ //타이틀이 비어있지 않으면
					map.put("title", title);
					map.put("rank", rank);
					map.put("image", image);
					map.put("openDate", openDate);
					array.add(map);
				}
			}
		}catch(Exception e){
			System.out.println("오류........."+e.toString());
		}
		return array;
	}
	
	@Autowired
	MovieDAO dao;
	
	@RequestMapping("/movie/insert")
	@ResponseBody
	public void insert(MovieVO vo){
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
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	      String wdate = sdf.format(new Date());
	      vo.setWdate(wdate);
	      vo.setImage(file);
		dao.insert(vo);
	}
	
	@RequestMapping("/movie/read")
	@ResponseBody
	public int read(String wdate,String rank){
		int result=0;
		MovieVO vo = dao.read(wdate,rank);
		if(vo!=null){
			result=1;
		}
		return result;
	}	
	@RequestMapping("movie")
	public String movie(){
		return "movie";
	}	
}
