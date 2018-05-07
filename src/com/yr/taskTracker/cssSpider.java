package com.yr.taskTracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.yr.JDBC.MysqlConnPool;
import cn.yr.JDBC.MysqlHelper;
import cn.yr.JDBC.MysqlUtil;
import cn.yr.entity.jobqueue;

import com.mongodb.DBCollection;
import com.yr.mongodb.mongodbCollection;
import com.yr.util.StringSplit;

public class cssSpider {
	public void spiderContentForCSS(jobqueue queue) {
		mongodbCollection m=new mongodbCollection();
		MysqlConnPool.getInstance();
		Connection connection = MysqlConnPool.getConnection();
		PreparedStatement ps = null;
		String url = queue.getUrl();
		if (queue.getState() != 1) {
			try {
				// 下载网页
				Document document = Jsoup
						.connect(url)
						.userAgent(
								"Mozilla/5.0 (Windows NT 6.1; rv:22.0) Gecko/20100101 Firefox/22.0")
						.timeout(5000).get();
				// urlrule规则
				// 用规则解析链接网页规则
				// 标题
				String titlerule = queue.getTitlerule();
				String title = StringSplit.getIndex(titlerule, document);
				System.out.println("标题:" + title);
				// 时间
				String publishTimerule = queue.getPublishtimerule();
				String publishTime = StringSplit.getIndex(publishTimerule,
						document);
				System.out.println("时间:" + publishTime);
				// 来源
				String sourcerule = queue.getSourcerule();
				String source = StringSplit.getIndex(sourcerule, document);
				System.out.println("来源:" + source);
				// 内容
				String contentrule = queue.getContentrule();
				String content = StringSplit.getIndex(contentrule, document);
				System.out.println("内容:" + content);
				if (title != null && source != null && publishTime != null
						&& content != null) {
					Date day = new Date();
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String gettime = df.format(day);
					/*String sql = "insert into crawlerdata(url,urlname,title,publishtime,gettime,source,content,sitetype) "
							+ "values('"
							+ url
							+ "','"
							+ queue.getUrlname()
							+ "','"
							+ title
							+ "','"
							+ publishTime
							+ "','"
							+ gettime
							+ "','"
							+ source
							+ "','"
							+ content
							+ "',"
							+ queue.getSitetype() + ")";
					int sucess = MysqlHelper.executeUpdate(connection, sql, ps);*/
					DBCollection init = m.init();
					int sucess = m.Addwebpage(init, title, source, publishTime, content);
					if (sucess == 1) {
						String sql1 = "update jobqueue set state=1 where url='"
								+ url + "'";
						MysqlHelper.executeUpdate(connection, sql1, ps);
					} else {
						String sql2 = "update jobqueue set state=2 where url='"
								+ url + "'";
						MysqlHelper.executeUpdate(connection, sql2, ps);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				String sql3 = "update jobqueue set state=2 where url='" + url
						+ "'";
				MysqlHelper.executeUpdate(connection, sql3, ps);
				e.printStackTrace();
			} 
				
			
		}MysqlUtil.realeaseResource(ps, connection);
	}

	public void getAsynchronization(jobqueue queue, String html) {
		mongodbCollection m=new mongodbCollection();
		MysqlConnPool.getInstance();
		Connection connection = MysqlConnPool.getConnection();
		PreparedStatement ps = null;
		if (queue.getState() != 1) {
			Document document = Jsoup.parse(html);
			String titlerule = queue.getTitlerule();
			String title = StringSplit.getIndex(titlerule, document);
			System.out.println("标题:" + title);
			String publishTimerule = queue.getPublishtimerule();
			String publishTime = StringSplit
					.getIndex(publishTimerule, document);
			System.out.println("时间:" + publishTime);
			String sourcerule = queue.getSourcerule();
			String source = StringSplit.getIndex(sourcerule, document);
			System.out.println("来源:" + source);
			String contentrule = queue.getContentrule();
			String content = StringSplit.getIndex(contentrule, document);
			System.out.println("内容:" + content);
			String url = queue.getUrl();
			if (title != null && source != null && publishTime != null
					&& content != null) {
				Date day = new Date();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String gettime = df.format(day);
				/*String sql = "insert into crawlerdata(url,urlname,title,publishtime,gettime,source,content,sitetype) "
						+ "values('"
						+ url
						+ "','"
						+ queue.getUrlname()
						+ "','"
						+ title
						+ "','"
						+ publishTime
						+ "','"
						+ gettime
						+ "','"
						+ source
						+ "','"
						+ content
						+ "',"
						+ queue.getSitetype()
						+ ")";
				int sucess = MysqlHelper.executeUpdate(connection, sql, ps);*/
				DBCollection init = m.init();
				int sucess =m.Addwebpage(init, title, source, publishTime, content);
				if (sucess == 1) {
					String sql1 = "update jobqueue set state=1 where url='"
							+ url + "'";
					MysqlHelper.executeUpdate(connection, sql1, ps);
				} else {
					String sql2 = "update jobqueue set state=2 where url='"
							+ url + "'";
					MysqlHelper.executeUpdate(connection, sql2, ps);
				}
			}
			MysqlUtil.realeaseResource(ps, connection);
		}
	}
}
