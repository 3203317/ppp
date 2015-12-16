package com.qingshu.core.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qingshu.base.pojo.JSTableField;
import com.qingshu.common.util.DataBaseUtils;
import com.qingshu.core.jdbc.config.Column;
public abstract class AbstractDao {
	/**
	 * create a new dao instance. default:mysql
	 */
	public static AbstractDao getInstance(String driverName) {
		if (driverName.contains("oracle")) {
			return new OracleDao();
		}
		return new MysqlDao();
	}

	
	/**
	 * query all table name
	 */
	public List<String> queryAllTables(String nativeSql) {
		List<String> list = new ArrayList<String>();
		try {
			Connection conn = DataBaseUtils.getConnection();
			ResultSet rs = createQuary(conn, nativeSql);
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * query all table name
	 */
	public abstract List<String> queryAllTables();
	
	/**
	 * 通用接口:获取表所有列信息,JDBC查询的结果集处理
	 */
	public abstract List<Column> queryColumns(String tableName);
	public abstract String getFieldDesc(String tableName,String fieldName);
	
	/**
	 * 数据列类型转换成Java类型
	 * @author xuyl
	 * @date 2013-3-1
	 * @param sqlType
	 * @return
	 */
	public abstract String typesConvert(String sqlType);
	
	/**
	 * 结果集ResultSet
	 */
	protected ResultSet createQuary(Connection conn, String sql) throws SQLException {
		return conn.createStatement().executeQuery(sql);
	}
	
}
