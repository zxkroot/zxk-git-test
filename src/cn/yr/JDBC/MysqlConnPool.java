package cn.yr.JDBC;
import com.mchange.v2.c3p0.ComboPooledDataSource;  

import java.beans.PropertyVetoException;  
import java.sql.Connection;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;  
  
/** 
 * Created by wuxueyou on 2017/2/23. 
 */  
  
public class MysqlConnPool {  
    
    private static final String[] mysqlmessage=Mysqlread.message;
    private static final MysqlConnPool instance = new MysqlConnPool();  
    private static ComboPooledDataSource comboPooledDataSource;  
  
    static {  
        try {  
            try {  
                Class.forName(mysqlmessage[0]);  
            } catch (ClassNotFoundException e) {  
                e.printStackTrace();  
            }  
            comboPooledDataSource = new ComboPooledDataSource();  
            comboPooledDataSource.setDriverClass(mysqlmessage[0]);  
            comboPooledDataSource.setJdbcUrl(mysqlmessage[1]);  
            comboPooledDataSource.setUser(mysqlmessage[2]);  
            comboPooledDataSource.setPassword(mysqlmessage[3]);  
            //下面是设置连接池的一配置  
            comboPooledDataSource.setMaxPoolSize(20);  
            comboPooledDataSource.setMinPoolSize(5);  
        } catch (PropertyVetoException e) {  
            e.printStackTrace();  
        }  
    }  
  
    @SuppressWarnings("finally")
	public synchronized static Connection getConnection() {  
        Connection connection = null;  
        try {  
            connection = comboPooledDataSource.getConnection();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } finally {  
            return connection;  
        }  
    }  
  
    private MysqlConnPool() {  
    }  
  
    public static MysqlConnPool getInstance() {  
        return instance;  
    }  
  
}  
