package com.example.domain;

public class MovieVO {
	private String wdate;
	private String title;
	private String rank;
	private String odate;
	private String image;
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getOdate() {
		return odate;
	}
	public void setOdate(String odate) {
		this.odate = odate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "MovieVO [wdate=" + wdate + ", title=" + title + ", rank=" + rank + ", odate=" + odate + ", image="
				+ image + "]";
	}	
	
}
