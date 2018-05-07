package cn.yr.JobClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import cn.yr.JDBC.MysqlConnPool;
import cn.yr.JDBC.MysqlHelper;
import cn.yr.JDBC.MysqlUtil;
import cn.yr.entity.Website;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import com.yr.redis.clientRedis;
import com.yr.util.HtmlUnitTest;
import com.yr.util.HttpClientParse;
import com.yr.util.HttpClientSimple;

public class xpath {
	public void getAll(Website site, String html) {
		MysqlConnPool.getInstance();
		Connection connection = MysqlConnPool.getConnection();
		clientRedis redis = new clientRedis();
		HttpClientSimple client =new HttpClientSimple();
		HttpClientParse parse=new HttpClientParse();
		String downHtml = client.DownHtml(html);
		ArrayList<String> list = parse.ParseByXpath(downHtml, site);
		PreparedStatement ps = null;
		for (String url : list) {
			if (redis.judge(url)) {
				continue;
			} else {
				redis.getRedis(url);
				if (!url.contains("http")) {
					url = site.getPreurl() + url;
				}
				System.out.println(url);

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
		}	MysqlUtil.realeaseResource(ps, connection);

	}

	public void getAsynchronization(Website site, String html) {
		
		MysqlConnPool.getInstance();
		Connection connection = MysqlConnPool.getConnection();
		clientRedis redis = new clientRedis();
		HtmlUnitTest unit = new HtmlUnitTest();

		ArrayList<String> list = unit.ParseByXpathContent(html,
				site.getUrlrule());
		PreparedStatement ps = null;
		for (String url : list) {
			if (redis.judge(url)) {
				continue;
			} else {
				redis.getRedis(url);
				if (!url.contains("http")) {
					url = site.getPreurl() + url;
				}
				System.out.println(url);

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
		}MysqlUtil.realeaseResource(ps, connection);
	}

}
