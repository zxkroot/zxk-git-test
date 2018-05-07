package com.yr.util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenHtmlCon {
	public WebDriver openWindow(String http){
		//设置浏览器驱动的位置，很重要，不然打开的话可能是空白页
		System.setProperty("webdriver.chrome.driver", "E:/work/eclipsework/SpiderToBigData/src/chromedriver.exe");
			
		WebDriver driver = new ChromeDriver();
		//设置url
		String url =http;
		//打开网页
		driver.get(url);
		return driver;
	}
}
