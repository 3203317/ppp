package com.qingshu.core.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qingshu.base.pojo.JSTableField;
import com.qingshu.common.util.DataBaseUtils;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.core.jdbc.config.Column;

public class MysqlDao extends AbstractDao {

	@Override
	public List<String> queryAllTables() {
		return queryAllTables("show tables");
	}

	@Override
	public List<Column> queryColumns(String tableName) {
		List<Column> list = new ArrayList<Column>();
		try {
			Connection conn = DataBaseUtils.getConnection();
			ResultSet rs = createQuary(conn, "show full fields from " + tableName);
			while (rs.next()) {
				String type = typesConvert(rs.getString(2));
				String javaStyle = ObjectUtils.javaStyle(rs.getString(1));
				list.add(new Column(type, rs.getString(1), javaStyle, rs.getString(9)));
			}
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String getFieldDesc(String tableName,String fieldName) {
		String fieldDesc = "";
		try {
			Connection conn = DataBaseUtils.getConnection();
			ResultSet rs = createQuary(conn, "show full fields from " + tableName);
			while (rs.next()) {
				if (rs.getString(1).equals(fieldName)) {
					fieldDesc=rs.getString(9);
				}
			}
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fieldDesc;
	}

	@Override
	public String typesConvert(String mysqlType) {
		if (mysqlType.startsWith("varchar") || mysqlType.startsWith("longtext")) {
			return "String";
		} else if (mysqlType.startsWith("int") || mysqlType.startsWith("bigint")) {
			return "Integer";
		} else if (mysqlType.startsWith("double")) {
			return "Double";
		} else if (mysqlType.startsWith("date")) {
			return "Date";
		}
		return mysqlType;
	}

	/**
	 * 测试入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractDao dao = new MysqlDao();
		List<String> tables = dao.queryAllTables();
		System.out.println(tables);
		List<Column> list = dao.queryColumns(tables.get(0));
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
