package com.yr.util;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.http.client.ClientProtocolException;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cn.yr.entity.Website;
import cn.yr.entity.jobqueue;



public class HttpClientParse {
	/**
	 * 采集url列表
	 * @param HtmlPage
	 * @param site
	 */
	public ArrayList<String> ParseByXpath(String HtmlPage,Website site){
		ArrayList<String> all=new ArrayList<>();
				try {
			HtmlCleaner hcCleaner = new HtmlCleaner();  
			 TagNode tagNode = hcCleaner.clean(HtmlPage);  
			 org.w3c.dom.Document dom = new DomSerializer(new CleanerProperties()).createDOM(tagNode);  
			 XPath xPath = XPathFactory.newInstance().newXPath();  
			 NodeList  blockList = (NodeList) xPath.evaluate(site.getUrlrule(), dom, XPathConstants.NODESET);
			  for (int i = 0; i < blockList.getLength(); i++) {
				  String  url=blockList.item(i).getTextContent();
				 // System.out.println("链接"+url);
				   //Node node = blockList.item(i);
//				   System.out.println("第"+i+"条开始内容为_____"+node.toString());				   
//				   System.out.println("链接："+xPath.evaluate(site.getUrlRule(), node,XPathConstants.STRING));
//				   System.out.println("第"+i+"条结束－－－－－－－－－－－－");
				  all.add(url);
			  }
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
				return all;  
	}
	/**
	 * 采集网页的内容和其它元素
	 * @param HtmlPage
	 * @param site
	 */
	public void ParseForContent(String HtmlPage2,jobqueue site2){
		try {
	 HtmlCleaner hcCleaner = new HtmlCleaner();  
	 TagNode tagNode = hcCleaner.clean(HtmlPage2);  
	 org.w3c.dom.Document dom = new DomSerializer(new CleanerProperties()).createDOM(tagNode);  
	 XPath xPath = XPathFactory.newInstance().newXPath(); 	
	 System.out.println("标题："+xPath.evaluate(site2.getTitlerule(), dom,XPathConstants.STRING));
	 System.out.println("内容："+xPath.evaluate(site2.getContentrule(), dom,XPathConstants.STRING));				   
	 System.out.println("发布时间："+xPath.evaluate(site2.getPublishtimerule(), dom,XPathConstants.STRING));
	 System.out.println("来源："+xPath.evaluate(site2.getSourcerule(), dom,XPathConstants.STRING));
	
	 
} catch (XPathExpressionException e) {
	e.printStackTrace();
} catch (ParserConfigurationException e) {
	e.printStackTrace();
}  
}
	public void ParseForContent1(String HtmlPage,jobqueue site){
		try {
	 HtmlCleaner hcCleaner = new HtmlCleaner();  
	 TagNode tagNode = hcCleaner.clean(HtmlPage);  
	 org.w3c.dom.Document dom = new DomSerializer(new CleanerProperties()).createDOM(tagNode);  
	 XPath xPath = XPathFactory.newInstance().newXPath(); 
	 NodeList  blockList = (NodeList) xPath.evaluate(site.getUrl(), dom, XPathConstants.NODESET);
	 for (int i = 0; i < blockList.getLength(); i++){
		 Node node = blockList.item(i);
	 System.out.println("标题："+xPath.evaluate(site.getTitlerule(), node,XPathConstants.STRING));
	 //System.out.println("内容："+xPath.evaluate(site.getContextRule(), node,XPathConstants.STRING));				   
	 System.out.println("发布时间："+xPath.evaluate(site.getTitlerule(), node,XPathConstants.STRING));
	 System.out.println("来源："+xPath.evaluate(site.getSourcerule(), node,XPathConstants.STRING));
	 }
	 
} catch (XPathExpressionException e) {
	e.printStackTrace();
} catch (ParserConfigurationException e) {
	e.printStackTrace();
}  
}
	public void ParseByXpathExample(String HtmlPage) {
		try {
			
			HtmlCleaner hcCleaner = new HtmlCleaner();  
			 TagNode tagNode = hcCleaner.clean(HtmlPage);  
			 org.w3c.dom.Document dom = new DomSerializer(new CleanerProperties()).createDOM(tagNode);  
			 XPath xPath = XPathFactory.newInstance().newXPath();  
			 NodeList  blockList = (NodeList) xPath.evaluate("//div[@class=\"searchListOne\"]/ul/li", dom, XPathConstants.NODESET);
			  for (int i = 0; i < blockList.getLength(); i++) {
				   Node node = blockList.item(i);
				   System.out.println("第"+i+"条开始内容为_____"+node.toString());
				   System.out.println("标题："+xPath.evaluate("div/h3", node,XPathConstants.STRING));
				   System.out.println("链接："+xPath.evaluate("div/h3//@href", node,XPathConstants.STRING));				   
				   System.out.println("时间："+xPath.evaluate("p[@class=\"source\"]/span[1]", node,XPathConstants.STRING));
				   System.out.println("来源："+xPath.evaluate("p[@class=\"source\"]/a[1]", node,XPathConstants.STRING));
				   
				  System.out.println("第"+i+"条结束－－－－－－－－－－－－");
			  }
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void SohuParseByXpathExample(String HtmlPage) {
		try {
			HtmlCleaner hcCleaner = new HtmlCleaner();  
			 TagNode tagNode = hcCleaner.clean(HtmlPage);  
			 org.w3c.dom.Document dom = new DomSerializer(new CleanerProperties()).createDOM(tagNode);  
			 XPath xPath = XPathFactory.newInstance().newXPath();  
			 NodeList  blockList = (NodeList) xPath.evaluate("//div[@class=\"searchListOne\"]/ul/li", dom, XPathConstants.NODESET);
			  for (int i = 0; i < blockList.getLength(); i++) {
				   Node node = blockList.item(i);
				   System.out.println("第"+i+"条开始内容为_____"+node.toString());
				   System.out.println("标题："+xPath.evaluate("div/h3", node,XPathConstants.STRING));
				   System.out.println("链接："+xPath.evaluate("div/h3//@href", node,XPathConstants.STRING));				   
				   System.out.println("时间："+xPath.evaluate("p[@class=\"source\"]/span[1]", node,XPathConstants.STRING));
				   System.out.println("来源："+xPath.evaluate("p[@class=\"source\"]/a[1]", node,XPathConstants.STRING));
				   
				  System.out.println("第"+i+"条结束－－－－－－－－－－－－");
			  }
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
