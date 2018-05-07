package cn.yr.JobClient;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.yr.util.CloseWindow;
import com.yr.util.GetPage;
import com.yr.util.OpenHtmlCon;
import com.yr.util.URLDecoder;

import cn.yr.JDBC.MysqlUtil;
import cn.yr.entity.Website;
import cn.yr.entity.searcherkeyword;

public class JobClient {
	// 循环website里的所有规则
	// 判断是否是站内搜索
	// 是站内搜索的话拼接网址
	public void Client(ArrayList<Website> site) {
		for (Website web : site) {
			int issearcher = web.getIssearcher();
			if (issearcher == 1) {
				System.out.println(web.getCrawerurl() + "  ：是站内搜索");
				List<String> spider = spider(web.getCrawerurl());
				for (String si : spider) {
					System.out.println(si);
					judgePage(web, si);
				}
			} else if (issearcher == 0) {
				System.out.println(web.getCrawerurl() + "  ：不是站内搜索");
				judgePage(web, web.getCrawerurl());
			}
		}
	}

	// 站内搜索匹配关键字
	public List<String> spider(String url) {
		URLDecoder coder = new URLDecoder();
		// String contentrule = web.getCrawerurl();//入口网址
		MysqlUtil my = new MysqlUtil();
		ArrayList<searcherkeyword> word = my.getkeyword();
		List<String> uri = new ArrayList<String>();
		for (searcherkeyword keyword : word) {
			String key = keyword.getKeyword();
			String encode = coder.Encode(key);
			String Crawerurl = url.replace("####", encode);
			uri.add(Crawerurl);
			}
		return uri;
	}

	// 爬取内容页
	// 循环采集每一页
	// 判断分页的规则，有两种，一种是@@@@，另一种是点击下一页
	public void judgePage(Website web, String url) {
		if (web.getPageone() != null && !"".equals(web.getPageone())) {
			String one = web.getPageone();
			getUrl(web, one);
			String url1 = url;
			for (int i = 2; i <= web.getPagenum(); i++) {
				url = url1.replace("@@@@", "" + i);
				System.out.println(url + "的第" + i + "页");
				getUrl(web, url);
			}
		} else {
			if (web.getState() == 1) {
				// 用四个@来写分页规则
				String url1 = url;
				for (int i = 1; i <= web.getPagenum(); i++) {
					url = url1.replace("@@@@", "" + i);
					System.out.println(url + "的第" + i + "页");
					getUrl(web, url);
				}
			} else if (web.getState() == 0) {
				// 需要点击下一页
				System.out.println("点击下一页");
				getHtml(web, url);
			}
		}
	}

	// 链接下载规则判断
	public void getUrl(Website site, String html) {
		JobClient job = new JobClient();
		xpath path = new xpath();
		css c = new css();
		if (site.getUrlwebtype() == 1) {
			System.out.println("静态");
			if (site.getRuletype() == 1) {
				System.out.println("xpath");
				path.getAll(site, html);

			} else if (site.getRuletype() == 2) {
				System.out.println("css");
				c.getAll(site, html);

			}
		} else if (site.getUrlwebtype() == 2) {
			System.out.println("异步加载");
			job.asynchronization(site, html);
		}

	}

	// 站内搜索
	public void fullCollect(Website site, String html) {
		JobClient job = new JobClient();
		MysqlUtil my = new MysqlUtil();
		ArrayList<searcherkeyword> word = my.getkeyword();
		for (searcherkeyword keyword : word) {
			String key = keyword.getKeyword();
			html = html.replace("####", key);
			System.out.println(html);
			job.getUrl(site, html);
		}
	}

	// 处理如果是异步加载链接页
	public void asynchronization(Website site, String http) {
		xpath path = new xpath();
		css c = new css();
		OpenHtmlCon open = new OpenHtmlCon();
		WebDriver driver = open.openWindow(http);
		GetPage page = new GetPage();
		String html = page.downHtml(driver);
		if (site.getRuletype() == 1) {
			System.out.println("xpath");
			path.getAsynchronization(site, html);
		} else if (site.getRuletype() == 2) {
			System.out.println("css");
			c.getAsynchronization(site, html);
		}
		CloseWindow close = new CloseWindow();
		close.closeWindow(driver);
	}

	// 点击下一页，得到所有的html
	public void getHtml(Website web, String url) {
		OpenHtmlCon open = new OpenHtmlCon();
		WebDriver driver = open.openWindow(url);
		xpath path = new xpath();
		css c = new css();
		GetPage page = new GetPage();

		// 多点一下
		for (int i = 1; i <= web.getPagenum(); i++) {
			System.out.println(url + "的第" + i + "页");
			String html = page.downHtml(driver);
			if (web.getRuletype() == 1) {
				System.out.println("xpath");
				path.getAsynchronization(web, html);
			} else if (web.getRuletype() == 2) {
				System.out.println("css");
				c.getAsynchronization(web, html);
			}
			driver.findElement(By.xpath(web.getPagenextxpath())).click();

		}
		CloseWindow close = new CloseWindow();
		close.closeWindow(driver);
	}
}
