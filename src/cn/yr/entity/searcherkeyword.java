package cn.yr.entity;

public class searcherkeyword {
	private int id;//关键字id
	private String keyword;//关键字
	public searcherkeyword(int id, String keyword) {
		super();
		this.id = id;
		this.keyword = keyword;
	}
	public searcherkeyword() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	

}
