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
	 * �ɼ�url�б�
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
				 // System.out.println("����"+url);
				   //Node node = blockList.item(i);
//				   System.out.println("��"+i+"����ʼ����Ϊ_____"+node.toString());				   
//				   System.out.println("���ӣ�"+xPath.evaluate(site.getUrlRule(), node,XPathConstants.STRING));
//				   System.out.println("��"+i+"������������������������������");
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
	 * �ɼ���ҳ�����ݺ�����Ԫ��
	 * @param HtmlPage
	 * @param site
	 */
	public void ParseForContent(String HtmlPage2,jobqueue site2){
		try {
	 HtmlCleaner hcCleaner = new HtmlCleaner();  
	 TagNode tagNode = hcCleaner.clean(HtmlPage2);  
	 org.w3c.dom.Document dom = new DomSerializer(new CleanerProperties()).createDOM(tagNode);  
	 XPath xPath = XPathFactory.newInstance().newXPath(); 	
	 System.out.println("���⣺"+xPath.evaluate(site2.getTitlerule(), dom,XPathConstants.STRING));
	 System.out.println("���ݣ�"+xPath.evaluate(site2.getContentrule(), dom,XPathConstants.STRING));				   
	 System.out.println("����ʱ�䣺"+xPath.evaluate(site2.getPublishtimerule(), dom,XPathConstants.STRING));
	 System.out.println("��Դ��"+xPath.evaluate(site2.getSourcerule(), dom,XPathConstants.STRING));
	
	 
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
	 System.out.println("���⣺"+xPath.evaluate(site.getTitlerule(), node,XPathConstants.STRING));
	 //System.out.println("���ݣ�"+xPath.evaluate(site.getContextRule(), node,XPathConstants.STRING));				   
	 System.out.println("����ʱ�䣺"+xPath.evaluate(site.getTitlerule(), node,XPathConstants.STRING));
	 System.out.println("��Դ��"+xPath.evaluate(site.getSourcerule(), node,XPathConstants.STRING));
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
				   System.out.println("��"+i+"����ʼ����Ϊ_____"+node.toString());
				   System.out.println("���⣺"+xPath.evaluate("div/h3", node,XPathConstants.STRING));
				   System.out.println("���ӣ�"+xPath.evaluate("div/h3//@href", node,XPathConstants.STRING));				   
				   System.out.println("ʱ�䣺"+xPath.evaluate("p[@class=\"source\"]/span[1]", node,XPathConstants.STRING));
				   System.out.println("��Դ��"+xPath.evaluate("p[@class=\"source\"]/a[1]", node,XPathConstants.STRING));
				   
				  System.out.println("��"+i+"������������������������������");
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
				   System.out.println("��"+i+"����ʼ����Ϊ_____"+node.toString());
				   System.out.println("���⣺"+xPath.evaluate("div/h3", node,XPathConstants.STRING));
				   System.out.println("���ӣ�"+xPath.evaluate("div/h3//@href", node,XPathConstants.STRING));				   
				   System.out.println("ʱ�䣺"+xPath.evaluate("p[@class=\"source\"]/span[1]", node,XPathConstants.STRING));
				   System.out.println("��Դ��"+xPath.evaluate("p[@class=\"source\"]/a[1]", node,XPathConstants.STRING));
				   
				  System.out.println("��"+i+"������������������������������");
			  }
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
