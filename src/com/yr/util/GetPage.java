package com.yr.util;
import java.io.UnsupportedEncodingException;

import org.openqa.selenium.WebDriver;

public class GetPage {
	
	public String downHtml(WebDriver driver){
		
		String html = "";
		try {
				Thread.sleep(10000);
				html = driver.getPageSource();
				
				//�滻ץȡ�����С�&nbsp;����Ϊ�ʺŵ�����
				
				html = new String(html.getBytes(),"GBK").replace('?', ' ').replace(' ', ' ');
		
				
//				System.out.println(html);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return html;
	}
}
