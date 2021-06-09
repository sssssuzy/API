package com.example.domain;

public class ShopVO {
	private String pid;
	private String pname;
	private String image;
	private int price;
	private String query;
	private String	pmall;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getPmall() {
		return pmall;
	}
	public void setPmall(String pmall) {
		this.pmall = pmall;
	}
	@Override
	public String toString() {
		return "ShopVO [pid=" + pid + ", pname=" + pname + ", image=" + image + ", price=" + price + ", query=" + query
				+ ", pmall=" + pmall + "]";
	}
	

}
