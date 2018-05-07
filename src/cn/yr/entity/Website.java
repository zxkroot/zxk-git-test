package cn.yr.entity;

public class Website {
	private int id;//id
	private String crawerurl;//采集入口
	private String urlrule;//采集网址规则
	private String preurl;//url前缀
	private String pageone;//采集网址第一页的连接
	private String titlerule;//采集标题规则
	private String sourcerule;//采集来源规则
	private String publishtimerule;//采集发布时间规则
	private String publishreg;//时间正则
	private String contentrule;//采集内容规则
	private String pagenextrule;//分页规则
	private String pagenextxpath;//下一页按钮 xpth规则
	private int sitetype;//网站类型
	private String sitename;//网站名称
	private int ruletype;//采集规则
	private int pagenum;//采集页数
	private int urlwebtype;//列表页类型
	private int contentwebtype;//内容页类型
	private int issearcher;//是否为站内搜索
	private int state;//状态，0特殊（要点击下一页） 1正常
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCrawerurl() {
		return crawerurl;
	}
	public void setCrawerurl(String crawerurl) {
		this.crawerurl = crawerurl;
	}
	public String getUrlrule() {
		return urlrule;
	}
	public void setUrlrule(String urlrule) {
		this.urlrule = urlrule;
	}
	public String getPreurl() {
		return preurl;
	}
	public void setPreurl(String preurl) {
		this.preurl = preurl;
	}
	public String getPageone() {
		return pageone;
	}
	public void setPageone(String pageone) {
		this.pageone = pageone;
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
	public String getPublishreg() {
		return publishreg;
	}
	public void setPublishreg(String publishreg) {
		this.publishreg = publishreg;
	}
	public String getContentrule() {
		return contentrule;
	}
	public void setContentrule(String contentrule) {
		this.contentrule = contentrule;
	}
	public String getPagenextrule() {
		return pagenextrule;
	}
	public void setPagenextrule(String pagenextrule) {
		this.pagenextrule = pagenextrule;
	}
	public String getPagenextxpath() {
		return pagenextxpath;
	}
	public void setPagenextxpath(String pagenextxpath) {
		this.pagenextxpath = pagenextxpath;
	}
	public int getSitetype() {
		return sitetype;
	}
	public void setSitetype(int sitetype) {
		this.sitetype = sitetype;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public int getRuletype() {
		return ruletype;
	}
	public void setRuletype(int ruletype) {
		this.ruletype = ruletype;
	}
	public int getPagenum() {
		return pagenum;
	}
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
	public int getUrlwebtype() {
		return urlwebtype;
	}
	public void setUrlwebtype(int urlwebtype) {
		this.urlwebtype = urlwebtype;
	}
	public int getContentwebtype() {
		return contentwebtype;
	}
	public void setContentwebtype(int contentwebtype) {
		this.contentwebtype = contentwebtype;
	}
	public int getIssearcher() {
		return issearcher;
	}
	public void setIssearcher(int issearcher) {
		this.issearcher = issearcher;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	
}
