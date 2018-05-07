package com.yr.util;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.jsoup.nodes.Document;

public class StringSplit {
	
	    public static String[] getString(String rule) {
	        //String sourceStr = "h1#h2#h3#h4#h5";
	        String[] sourceStrArray = rule.split("@@@@");
//	        for (int i = 0; i < sourceStrArray.length; i++) {
//	            System.out.println(sourceStrArray[i]);
//	        }
            //鏈�澶氬垎鍓插嚭3涓瓧绗︿覆
	        /*int maxSplit = 3;
	        sourceStrArray = sourceStr.split("#", maxSplit);
	        for (int i = 0; i < sourceStrArray.length; i++) {
	            System.out.println(sourceStrArray[i]);
	        }*/
			return sourceStrArray;
	}
	    public static String getContent(Document document,String[] rule){
	    	String ByUrlGetContent=null;
	    	   for(String con:rule){
	    		   ByUrlGetContent = document.select(con).text();
	    		   if(ByUrlGetContent.length()!=0){
	    			   break;
	    		   }
	    	   }
	        return ByUrlGetContent;
	    }
	    public static String getIndex(String url,Document document){
	    	String newUrl=null;
	    	if(url.indexOf("%")!=-1){
				String[] titleRule=url.split("%");
				newUrl=StringSplit.getContent(document, titleRule);
				}else{
					newUrl=document.select(url).text();
				}
	    	
			return newUrl;
	     }
	    public static String getIndexforXpath(String url,XPath xPath,org.w3c.dom.Document dom) throws XPathExpressionException{
	    	String newUrl=null;
	    	if(url.indexOf("%")!=-1){
	    		String[] titleRule=url.split("%");
	    		newUrl=StringSplit.getContentforXpath(dom, titleRule, xPath);
	    	}else{
	    		newUrl=((String) xPath.evaluate(url, dom,XPathConstants.STRING)).trim();
	    	}
			return newUrl;
	    }
	    public static String getContentforXpath(org.w3c.dom.Document dom,String[] rule,XPath xPath) throws XPathExpressionException{
	    	String ByUrlGetContent=null;
	    	   for(String con:rule){
	    		   ByUrlGetContent = ((String) xPath.evaluate(con, dom,XPathConstants.STRING)).trim();
	    		   if(ByUrlGetContent.length()!=0){
	    			   break;
	    		   }
	    	   }
	        return ByUrlGetContent;
	    }

}
