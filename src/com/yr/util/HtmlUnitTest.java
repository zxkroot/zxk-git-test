package com.yr.util;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnitTest {

    public  HtmlPage  getUrlByUnit(String html) {
    	HtmlPage htmlPage = null;
    	try {
        	
            WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3);  
            webClient.setJavaScriptEnabled(false);
            webClient.setCssEnabled(false);
            webClient.setRedirectEnabled(true);
            webClient.setThrowExceptionOnScriptError(false);
            webClient.setTimeout(50000);
            htmlPage = webClient.getPage(html);
            webClient.waitForBackgroundJavaScript(10000);

        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
            
        } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return htmlPage;


    }
    public  ArrayList<String> ParseByXpathUrl(HtmlPage htmlPage,String urlrule) {
		ArrayList<String> list = new ArrayList<>();
		try {	
			          
            String body=htmlPage.asXml();
//            System.out.println(body);
            HtmlCleaner hCleaner = new HtmlCleaner();
    		TagNode tagNode = hCleaner.clean(body);
			Document dom = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList blockList = (NodeList) xPath.evaluate(urlrule,dom , XPathConstants.NODESET);
			for(int i = 0;i<blockList.getLength();i++){
				String url = blockList.item(i).getTextContent();
				list.add(url);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;	   
 	}
    public  ArrayList<String> ParseByXpathContent(String HtmlPage,String urlrule) {
    	ArrayList<String> AllHttp=new ArrayList<String>();
 		try {
 			 HtmlCleaner hCleaner = new HtmlCleaner();
 			 TagNode tagNode = hCleaner.clean(HtmlPage); 						
 			 Document dom = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
 			XPath xPath = XPathFactory.newInstance().newXPath();

 			String http = (String) xPath.evaluate(urlrule, dom,XPathConstants.STRING);
			
			System.out.println("链接:"+http);
			AllHttp.add(http);
			
 		} catch (XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AllHttp;						   
 	}
}