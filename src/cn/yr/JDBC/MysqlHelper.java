package cn.yr.JDBC;
import org.apache.commons.lang3.StringUtils;  

import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
  
/** 
 * Created by wuxueyou on 2017/3/1. 
 */  
  
public class MysqlHelper {  
    /** 
     * 执行update、insert、delete等语句，返回值为受影响的行数 
     * 
     * @param connection 
     * @param sql 
     * @return 
     */  
    public static int executeUpdate(Connection connection, String sql,PreparedStatement ps) {  
        int resCount = 0;  
        if (StringUtils.isBlank(sql)) {  
            System.out.println("sql语句不能为空");  
            return resCount;  
        }  
          
        System.out.println("sql--> " + sql);  
        try {  
            ps = connection.prepareStatement(sql);  
            resCount = ps.executeUpdate();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } finally {
        	
        }  
        return resCount;  
  
    }  
  
    /** 
     * 执行能够返回结果集的查询语句 
     * 
     * @param connection 
     * @param sql 
     * @return 
     */  
    public static ResultSet executeQuery(Connection connection, String sql,PreparedStatement ps,ResultSet rs) {  
        if (StringUtils.isBlank(sql)) {  
            System.out.println("sql语句不为空");  
            return null;  
        }  
         
          
        System.out.println("sql--> " + sql);  
        try {  
            ps = connection.prepareStatement(sql);  
            rs = ps.executeQuery();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
        return rs;  
    }  
}  