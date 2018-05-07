
package com.yr.taskTracker;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import com.yr.util.CloseWindow;
import com.yr.util.GetPage;
import com.yr.util.OpenHtmlCon;

import cn.yr.entity.jobqueue;



public class taskTrackerAll {
	public void spiderContent(ArrayList<jobqueue> queue){
		taskTrackerAll all=new taskTrackerAll();
		xpathSpider Xpath=new xpathSpider();
		cssSpider CSS=new cssSpider();
		for(jobqueue job:queue){
			System.out.println(job.getUrl());
			if(job.getContentwebtype()==1){
				System.out.println("静态");
				if(job.getRuletype()==1){
					System.out.println("xpath");
					Xpath.spiderContentForXpath(job);
				}else if(job.getRuletype()==2){
					System.out.println("css");
					CSS.spiderContentForCSS(job);
				}
			}else if(job.getContentwebtype()==2){
				System.out.println("异步");
				all.contentForasynchronization(job);
			}
			
		}
	}
	public  void contentForasynchronization(jobqueue job){
		xpathSpider Xpath=new xpathSpider();
		cssSpider CSS=new cssSpider();
		OpenHtmlCon open=new OpenHtmlCon();
		WebDriver driver=open.openWindow(job.getUrl());
		GetPage page = new GetPage();
		String html = page.downHtml(driver);
		if(job.getRuletype()==1){
			System.out.println("xpath");
			Xpath.ParseByXpathData(html, job);
		}else if(job.getRuletype()==2){
			System.out.println("css");
			CSS.getAsynchronization(job, html);
		}
		CloseWindow close = new CloseWindow();
		close.closeWindow(driver);
	}
}
