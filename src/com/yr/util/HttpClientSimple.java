package com.yr.util;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpEntity;  
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;  
import org.apache.http.client.config.RequestConfig;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.conn.ssl.DefaultHostnameVerifier;  
import org.apache.http.conn.util.PublicSuffixMatcher;  
import org.apache.http.conn.util.PublicSuffixMatcherLoader;  
import org.apache.http.entity.ContentType;  
import org.apache.http.entity.StringEntity;  
import org.apache.http.entity.mime.MultipartEntityBuilder;  
import org.apache.http.entity.mime.content.FileBody;  
import org.apache.http.entity.mime.content.StringBody;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;  
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class HttpClientSimple {
	private static RequestConfig requestConfig = RequestConfig.custom()  
            .setSocketTimeout(15000)  
            .setConnectTimeout(15000)  
            .setConnectionRequestTimeout(15000)  
            .build();  
	 static CloseableHttpClient httpClient = null;  
     static CloseableHttpResponse response = null;
     static HttpEntity entity = null;   
     public String DownHtml(String httpUrl){
    	// 创建默认的httpClient实例. 
		// String httpUrl="http://search.tianya.cn/bbs?q=%E6%B2%B3%E5%8C%97";
		 httpClient = HttpClients.createDefault(); 
		 HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求 	
		 httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
		 httpGet.setConfig(requestConfig);  
		 httpGet.setProtocolVersion(HttpVersion.HTTP_1_0);
		 httpGet.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
            // 执行请求  
		 String  responseContent="";
		 try{
         response = httpClient.execute(httpGet);  
         entity = response.getEntity();  
         responseContent = EntityUtils.toString(entity, "UTF-8"); 
		 }catch(Exception e){
			 e.getStackTrace();
		 }
    	 return  responseContent;
    	 
     }
     
     public void closeClient(CloseableHttpClient httpClient,CloseableHttpResponse response){
    	 // 关闭连接,释放资源  
    	 try {  
         	
             // 关闭连接,释放资源  
             if (response != null) {  
                 response.close();  
             }  
             if (httpClient != null) {  
                 httpClient.close();  
             }  
         } catch (IOException e) {  
             e.printStackTrace();  
         }  
    	 
     }
	
}
