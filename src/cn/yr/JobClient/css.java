package cn.yr.JobClient;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yr.redis.clientRedis;

import cn.yr.JDBC.MysqlConnPool;
import cn.yr.JDBC.MysqlHelper;
import cn.yr.JDBC.MysqlUtil;
import cn.yr.entity.Website;

public class css {
	// 下载链接页并把href提取出来 入库
	public void getAll(Website site, String html) {
		MysqlConnPool.getInstance();
		Connection connection = MysqlConnPool.getConnection();
		clientRedis redis = new clientRedis();
		Document document = null;
		try {
			document = Jsoup
					.connect(html)
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.1; rv:22.0) Gecko/20100101 Firefox/22.0")
					.get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement ps = null;
		Elements elements = document.select(site.getUrlrule());
		for (Element ele : elements) {
			String http = ele.attr("href");
			if (redis.judge(http)) {
				continue;
			} else {
				redis.getRedis(http);
				if (!http.contains("http")) {
					http = site.getPreurl() + http;
				}
				System.out.println(http);
				String url = http;
				String titlerule = site.getTitlerule();
				String sourcerule = site.getSourcerule();
				String publishtime = site.getPublishtimerule();
				String contentrule = site.getContentrule();
				String crawerurl = site.getCrawerurl();
				String sitename = site.getSitename();
				int sitetype = site.getSitetype();
				int ruletype = site.getRuletype();
				int contentwebtype = site.getContentwebtype();
				System.out.println("url" + url);
				System.out.println("titlerule" + titlerule);
				System.out.println("sourcerule" + sourcerule);
				System.out.println("publishtime" + publishtime);
				System.out.println("contentrule" + contentrule);
				String sql = "insert into jobqueue(url,titlerule,publishtimerule,sourcerule,contentrule,sitetype,ruletype,contentwebtype,crawerurl,sitename) "
						+ "values('"
						+ url 
						+ "','"
						+ titlerule
						+ "','"
						+ publishtime
						+ "','"
						+ sourcerule
						+ "','"
						+ contentrule
						+ "',"
						+ sitetype
						+ ","
						+ ruletype
						+ ","
						+ contentwebtype
						+ ",'"
						+ crawerurl
						+ "','"
						+ sitename
						+ "')";
				
				MysqlHelper.executeUpdate(connection, sql, ps);
				
				
			}
		}
		MysqlUtil.realeaseResource(ps, connection);
	}

	public void getAsynchronization(Website site, String html) {


		MysqlConnPool.getInstance();
		Connection connection = MysqlConnPool.getConnection();
		clientRedis redis = new clientRedis();
		Document document = Jsoup.parse(html);
		PreparedStatement ps = null;
		Elements elements = document.select(site.getUrlrule());
		for (Element ele : elements) {
			String http = ele.attr("href");
			if (redis.judge(http)) {
				continue;
			} else {
				redis.getRedis(http);
				if (!http.contains("http")) {
					http = site.getPreurl() + http;
				}
				System.out.println(http);
				String url = http;
				String titlerule = site.getTitlerule();
				String sourcerule = site.getSourcerule();
				String publishtime = site.getPublishtimerule();
				String contentrule = site.getContentrule();
				String crawerurl = site.getCrawerurl();
				String sitename = site.getSitename();
				int sitetype = site.getSitetype();
				int ruletype = site.getRuletype();
				int contentwebtype = site.getContentwebtype();
				System.out.println("url" + url);
				System.out.println("titlerule" + titlerule);
				System.out.println("sourcerule" + sourcerule);
				System.out.println("publishtime" + publishtime);
				System.out.println("contentrule" + contentrule);

				String sql = "insert into jobqueue(url,titlerule,publishtimerule,sourcerule,contentrule,sitetype,ruletype,contentwebtype,crawerurl,sitename) "
						+ "values('"
						+ url
						+ "','"
						+ titlerule
						+ "','"
						+ publishtime
						+ "','"
						+ sourcerule
						+ "','"
						+ contentrule
						+ "',"
						+ sitetype
						+ ","
						+ ruletype
						+ ","
						+ contentwebtype
						+ ",'"
						+ crawerurl
						+ "','"
						+ sitename
						+ "')";
				
				MysqlHelper.executeUpdate(connection, sql, ps);
				
			}
		}
		MysqlUtil.realeaseResource(ps, connection);
	}

}
