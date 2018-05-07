package com.yr.taskTracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;

import cn.yr.JDBC.MysqlConnPool;
import cn.yr.JDBC.MysqlHelper;
import cn.yr.JDBC.MysqlUtil;
import cn.yr.entity.jobqueue;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.mongodb.DBCollection;
import com.yr.mongodb.mongodbCollection;
import com.yr.util.HtmlUnitTest;
import com.yr.util.StringSplit;

public class xpathSpider {
	public void spiderContentForXpath(jobqueue queue) {
		HtmlUnitTest unit = new HtmlUnitTest();
		HtmlPage htmlPage = unit.getUrlByUnit(queue.getUrl());
		String body = htmlPage.asXml();
		xpathSpider xpth = new xpathSpider();
		xpth.ParseByXpathData(body, queue);

	}

	public void ParseByXpathData(String HtmlPage, jobqueue queue) {
		mongodbCollection m= new mongodbCollection();
		MysqlConnPool.getInstance();
		Connection connection = MysqlConnPool.getConnection();
		PreparedStatement ps = null;
		if (queue.getState() != 1) {
			try {
				HtmlCleaner hCleaner = new HtmlCleaner();
				TagNode tagNode2 = hCleaner.clean(HtmlPage);
				Document dom = new DomSerializer(new CleanerProperties())
						.createDOM(tagNode2);
				XPath xPath = XPathFactory.newInstance().newXPath();

				String titlerule = queue.getTitlerule();
				String title = StringSplit.getIndexforXpath(titlerule, xPath,
						dom);
				System.out.println(title);
				String publishTimerule = queue.getPublishtimerule();
				String publishtime = StringSplit.getIndexforXpath(
						publishTimerule, xPath, dom);
				System.out.println(publishtime);
				String sourcerule = queue.getSourcerule();
				String source = StringSplit.getIndexforXpath(sourcerule, xPath,
						dom);
				System.out.println(source);
				String contentrule = queue.getContentrule();
				String content = StringSplit.getIndexforXpath(contentrule,
						xPath, dom);
				System.out.println(content);
				if (title != null && source != null && publishtime != null
						&& content != null) {
					Date day = new Date();
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String gettime = df.format(day);
					/*String sql = "insert into crawlerdata(url,urlname,title,publishtime,gettime,source,content,sitetype) "
							+ "values('"
							+ queue.getUrl()
							+ "','"
							+ queue.getUrlname()
							+ "','"
							+ title
							+ "','"
							+ publishtime
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
					int sucess =m.Addwebpage(init, title, source, publishtime, content);
					if (sucess == 1) {
						String sql1 = "update jobqueue set state=1 where url='"
								+ queue.getUrl() + "'";
						MysqlHelper.executeUpdate(connection, sql1, ps);
					} else {
						String sql2 = "update jobqueue set state=2 where url='"
								+ queue.getUrl() + "'";
						MysqlHelper.executeUpdate(connection, sql2, ps);
					}
				}
			} catch (XPathExpressionException | ParserConfigurationException e) {
				// TODO Auto-generated catch block
				String sql3 = "update jobqueue set state=2 where url='"
						+ queue.getUrl() + "'";
				MysqlHelper.executeUpdate(connection, sql3, ps);
				e.printStackTrace();
			} 				
			MysqlUtil.realeaseResource(ps, connection);
		}
	}

}
