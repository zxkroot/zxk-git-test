package com.yr.SystemStart;



import com.yr.taskTracker.taskTrackerAll;

import cn.yr.JDBC.MysqlUtil;
import cn.yr.JobClient.JobClient;

public class Start {
	public static void main(String[] args) {
		JobClient client=new JobClient();
		MysqlUtil my=new MysqlUtil();
		client.Client(my.getAll());
		taskTrackerAll task=new taskTrackerAll();
   		task.spiderContent(my.getqueue());
	}
	
}
