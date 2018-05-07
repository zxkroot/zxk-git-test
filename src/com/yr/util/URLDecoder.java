package com.yr.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLDecoder {
	/**
	 * 把中文转换为URL编码
	 * @param chinese
	 * @return
	 */
	public String Encode(String chinese){
		 String urlStr = null;
		try {
			//转换成普通字符串
		   //必须强调的是编码方式必须正确，如baidu的是gb2312，oogle的是UTF-8
			urlStr = URLEncoder.encode(chinese, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
		 return urlStr;
	}
	
	public String Decode(String url){
		 String urlStr = null;
		try {
			urlStr = java.net.URLDecoder.decode(url, "gb2312");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     System.out.println(urlStr);
		 return urlStr;
	}
	
	
    public static void main(String[] args)  {
    	URLDecoder  coder = new URLDecoder();
    	coder.Encode("山西");
    	
    }

}