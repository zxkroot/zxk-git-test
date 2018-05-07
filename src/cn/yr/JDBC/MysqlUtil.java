package cn.yr.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import cn.yr.entity.Website;
import cn.yr.entity.jobqueue;
import cn.yr.entity.searcherkeyword;

public class MysqlUtil {
	public ArrayList<Website> getAll() {
		ArrayList<Website> web = new ArrayList<Website>();
		MysqlConnPool.getInstance();
		Connection connection = MysqlConnPool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		rs = MysqlHelper.executeQuery(connection,
				"select * from website ", ps, rs);
		try {
			while (rs.next()) {
				Website site = new Website();
				site.setId(rs.getInt("id"));
				site.setCrawerurl(rs.getString("crawerurl"));
				site.setUrlrule(rs.getString("urlrule"));
				site.setPreurl(rs.getString("preurl"));
				site.setPageone(rs.getString("pageone"));
				site.setTitlerule(rs.getString("titlerule"));
				site.setSourcerule(rs.getString("sourcerule"));
				site.setPublishtimerule(rs.getString("publishtimerule"));
				site.setPublishreg(rs.getString("publishreg"));
				site.setContentrule(rs.getString("contentrule"));
				site.setPagenextrule(rs.getString("pagenextrule"));
				site.setPagenextxpath(rs.getString("pagenextxpath"));
				site.setSitetype(rs.getInt("sitetype"));
				site.setSitename(rs.getString("sitename"));
				site.setRuletype(rs.getInt("ruletype"));
				site.setPagenum(rs.getInt("pagenum"));
				site.setUrlwebtype(rs.getInt("urlwebtype"));
				site.setContentwebtype(rs.getInt("contentwebtype"));
				site.setIssearcher(rs.getInt("issearcher"));
				site.setState(rs.getInt("state"));
				web.add(site);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MysqlUtil.realeaseResource(rs, ps, connection);
		}
		return web;

	}

	// 获取关键词
	public ArrayList<searcherkeyword> getkeyword() {
		// TODO Auto-generated method stub
		ArrayList<searcherkeyword> key = new ArrayList<searcherkeyword>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		MysqlConnPool.getInstance();
		Connection connection = MysqlConnPool.getConnection();
		rs = MysqlHelper.executeQuery(connection,
				"select * from  searcherkeyword", ps, rs);
		try {
			while (rs.next()) {
				searcherkeyword word = new searcherkeyword();
				word.setId(rs.getInt("id"));
				word.setKeyword(rs.getString("keyword"));
				key.add(word);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MysqlUtil.realeaseResource(rs, ps, connection);
		}

		return key;

	}

	// 释放资源
	public static void realeaseResource(ResultSet rs, PreparedStatement ps,
			Connection conn) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (null != ps) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 释放资源
	public static void realeaseResource(PreparedStatement ps, Connection conn) {

		if (null != ps) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<jobqueue> getqueue() {
		// TODO Auto-generated method stub
		ArrayList<jobqueue> job = new ArrayList<jobqueue>();
		MysqlConnPool.getInstance();
		Connection connection = MysqlConnPool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from  jobqueue";
		rs = MysqlHelper.executeQuery(connection, sql, ps, rs);
		try {
			while (rs.next()) {
				jobqueue queue = new jobqueue();
				queue.setId(rs.getInt("id"));
				queue.setUrl(rs.getString("url"));
				queue.setUrlname(rs.getString("sitename"));
				queue.setTitlerule(rs.getString("titlerule"));
				queue.setSourcerule(rs.getString("sourcerule"));
				queue.setPublishtimerule(rs.getString("publishtimerule"));
				queue.setContentrule(rs.getString("contentrule"));
				queue.setState(rs.getInt("state"));
				queue.setSitetype(rs.getInt("sitetype"));
				queue.setRuletype(rs.getInt("ruletype"));
				queue.setContentwebtype(rs.getInt("contentwebtype"));
				job.add(queue);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MysqlUtil.realeaseResource(rs, ps, connection);
		}
		return job;

	}
}
