package com.yr.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;


import cn.yr.entity.jobqueue;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
public class mongodbCollection {
	public Mongo mg = null;
    public DB db;
    public DBCollection users;
    
    public DBCollection init() {
        try {
          mg = new Mongo("localhost", 27017);
        } catch (MongoException e) {
            e.printStackTrace();
        }
        //获取footbar DB；如果默认没有创建，mongodb会自动创建
        db = mg.getDB("spider");
        return users = db.getCollection("webpage");
    }
    public DBCollection init2() {
        try {
          mg = new Mongo("localhost", 27017);
        } catch (MongoException e) {
            e.printStackTrace();
        }
        //获取footbar DB；如果默认没有创建，mongodb会自动创建
        db = mg.getDB("spider");
        return users = db.getCollection("jobqueue");
    }
    public void Addjobqueue(DBCollection users,String url,String titlerule,String sourcerule,String publishtimerule,String contentrule,int type) {  
        DBObject user = new BasicDBObject();
        user.put("url", url);
        user.put("title", titlerule);
        user.put("source", sourcerule);  
        user.put("Publishtime", publishtimerule);  
        user.put("content", contentrule); 
        user.put("type", type);
        user.put("state", 0);
        users.save(user);  
        //查看是否添加成功  
//        DBCursor cur = users.find();        
//        while (cur.hasNext()) {  
//            System.out.println(cur.next());  
//        }  
    }
    public ArrayList<jobqueue> findAll() {  
        Mongo mongo = new Mongo();  
        DB myMongo = mongo.getDB("spider");  
        DBCollection userCollection = myMongo.getCollection("jobqueue");  
        DBCursor cursor=userCollection.find();  
        ArrayList<jobqueue> userList = new ArrayList<jobqueue>();  
        while(cursor.hasNext()){  
        	jobqueue user = new jobqueue();  
             user.parse(cursor.next());  
             userList.add(user);  
        }  
        return userList;  
    }  
    public void update(String href){         
        users.update(  
             new BasicDBObject("url", href),           
             new BasicDBObject("$set" ,new BasicDBObject("state",1)),  
             false,//如果users中不存在age字段，是否更新，false表示不更新  
             false//只修改第一条，true表示修改多条  
        );        
   }   
    public void update2(String href){         
        users.update(  
             new BasicDBObject("url", href),           
             new BasicDBObject("$set" ,new BasicDBObject("state",2)),  
             false,//如果users中不存在age字段，是否更新，false表示不更新  
             false//只修改第一条，true表示修改多条  
        );        
   }   
    public int Addwebpage(DBCollection users,String titlerule,String sourcerule,String publishtimerule,String contentrule) {  
        DBObject user = new BasicDBObject();
        user.put("title", titlerule);
        user.put("source", sourcerule);  
        user.put("Publishtime", publishtimerule);  
        user.put("content", contentrule); 
        users.save(user);  
        //查看是否添加成功  
        int num=0;
        DBCursor cur = users.find(user); 
        
        while (cur.hasNext()) {  
        	num=1;
            System.out.println(cur.next());  
        }  
        return num;
    }
    
}

