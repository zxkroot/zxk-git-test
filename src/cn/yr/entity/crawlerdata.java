package cn.yr.entity;

public class crawlerdata {
	private int id;
	private String url;//来源网站
	private String urlname;//网站名称
	private String title;//标题
	private String scource;//来源
	private String publishtime;//时间
	private String content;//内容
	private String gettime;//爬取时间
	private int sitetype;//网站类型
	private int flag;//已读标识
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlname() {
		return urlname;
	}
	public void setUrlname(String urlname) {
		this.urlname = urlname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getScource() {
		return scource;
	}
	public void setScource(String scource) {
		this.scource = scource;
	}
	public String getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getGettime() {
		return gettime;
	}
	public void setGettime(String gettime) {
		this.gettime = gettime;
	}
	public int getSitetype() {
		return sitetype;
	}
	public void setSitetype(int sitetype) {
		this.sitetype = sitetype;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	

}
