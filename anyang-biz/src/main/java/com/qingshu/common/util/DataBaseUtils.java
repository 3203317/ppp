package com.qingshu.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * 数据库连接辅助类
 */
public class DataBaseUtils {
	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("jdbc");
	private static Connection connection;

	/**
	 * 获取数据库连接
	 */
	public static synchronized Connection getConnection() {
		String user = bundle.getString("jdbc.username");
		String password = bundle.getString("jdbc.password");
		String driverClassName = bundle.getString("jdbc.driverClassName");
		String url = bundle.getString("jdbc.url");
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.print("找不到驱动");
		} catch (SQLException e) {
			System.out.print("连接数据库失败");
		}
		return connection;

	}

	public static void closeConnection() {
		try {
			if ((connection != null) && (!connection.isClosed()))
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public static void closePreparedStatement(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pstmt = null;
		}
	}

	public static void closeStatement(Statement stmt) {
		if (stmt != null)
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		PreparedStatement pst = null;
		try {
			pst = getConnection().prepareStatement("select * from j_s_table where 1=1");
			ResultSetMetaData rsd = pst.executeQuery().getMetaData();
			for (int i = 0; i < rsd.getColumnCount(); i++) {
				System.out.print("java类型：" + rsd.getColumnClassName(i + 1));
				System.out.print("  数据库类型:" + rsd.getColumnTypeName(i + 1));
				System.out.print("  字段名称:" + rsd.getColumnName(i + 1));
				System.out.print("  字段长度:" + rsd.getColumnDisplaySize(i + 1));
				System.out.println();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				pst.close();
				pst = null;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

}
