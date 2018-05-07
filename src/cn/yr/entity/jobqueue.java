package cn.yr.entity;

import com.mongodb.DBObject;

public class jobqueue {
	private int id ;
	private String urlname;
	private String url;
	private String titlerule;
	private String sourcerule;
	private String publishtimerule;
	private String contentrule;
	private int state;
	private int sitetype;
	private int ruletype;
	private int contentwebtype;
	
	public jobqueue() {
		super();
		// TODO Auto-generated constructor stub
	}
	public jobqueue(int id, String urlname, String url, String titlerule,
			String sourcerule, String publishtimerule, String contentrule,
			int state, int sitetype, int ruletype, int contentwebtype) {
		super();
		this.id = id;
		this.urlname = urlname;
		this.url = url;
		this.titlerule = titlerule;
		this.sourcerule = sourcerule;
		this.publishtimerule = publishtimerule;
		this.contentrule = contentrule;
		this.state = state;
		this.sitetype = sitetype;
		this.ruletype = ruletype;
		this.contentwebtype = contentwebtype;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrlname() {
		return urlname;
	}
	public void setUrlname(String urlname) {
		this.urlname = urlname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitlerule() {
		return titlerule;
	}
	public void setTitlerule(String titlerule) {
		this.titlerule = titlerule;
	}
	public String getSourcerule() {
		return sourcerule;
	}
	public void setSourcerule(String sourcerule) {
		this.sourcerule = sourcerule;
	}
	public String getPublishtimerule() {
		return publishtimerule;
	}
	public void setPublishtimerule(String publishtimerule) {
		this.publishtimerule = publishtimerule;
	}
	public String getContentrule() {
		return contentrule;
	}
	public void setContentrule(String contentrule) {
		this.contentrule = contentrule;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getSitetype() {
		return sitetype;
	}
	public void setSitetype(int sitetype) {
		this.sitetype = sitetype;
	}
	public int getRuletype() {
		return ruletype;
	}
	public void setRuletype(int ruletype) {
		this.ruletype = ruletype;
	}
	public int getContentwebtype() {
		return contentwebtype;
	}
	public void setContentwebtype(int contentwebtype) {
		this.contentwebtype = contentwebtype;
	}
	public void parse(DBObject dbo){  
		this.setUrl ((String)dbo.get("url"));
		this.setTitlerule ((String)dbo.get("title"));
		this.setSourcerule ((String)dbo.get("source"));
		this.setPublishtimerule((String)dbo.get("Publishtime"));
		this.setContentrule((String)dbo.get("content"));
		this.setState((Integer)dbo.get("state"));
		
    }  

}
