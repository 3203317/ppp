package com.qingshu.core.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qingshu.base.pojo.JSTableField;
import com.qingshu.common.util.DataBaseUtils;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.core.jdbc.config.Column;

import freemarker.template.utility.StringUtil;

/**
 * MySQL database Dao
 * @author xuyl
 * @date 2013-1-7
 */
public class OracleDao extends AbstractDao{
	
	@Override
	public List<String> queryAllTables() {
		return queryAllTables("select lower(tname) from tab where tabtype = 'TABLE'");
	}

	@Override
	public List<Column> queryColumns(String tableName) {
		List<Column> list = new ArrayList<Column>();
		try {
			Connection conn = DataBaseUtils.getConnection();
			String sql = 
					"select  lower(t1.column_name), lower(t1.data_type),  t2.comments " +
					" from  user_col_comments  t2  left  join  user_tab_columns  t1 " + 
					" on  t1.table_name  =  t2.table_name  and  t1.column_name  =  t2.column_name " + 
					" where  t1.table_name  =  upper('"+tableName+"')"; 
			ResultSet rs = createQuary(conn, sql);
			while (rs.next()) {
				String type = typesConvert(rs.getString(2));
				String javaStyle = ObjectUtils.javaStyle(rs.getString(1));
				list.add(new Column(type, rs.getString(1), javaStyle, rs.getString(3)));
			}
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public String typesConvert(String mysqlType) {
		if (mysqlType.startsWith("varchar") || mysqlType.startsWith("char")) {
			return "String";
		} else if (mysqlType.startsWith("long")) {
			return "Integer";
		} else if (mysqlType.startsWith("number")) {
			return "Double";
		} else if (mysqlType.startsWith("date")) {
			return "Date";
		} 
		return mysqlType;
	}
	
	/**
	 * 测试入口
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractDao dao = new OracleDao();
		System.out.println("===============================" + dao.queryAllTables());
		List<Column> list = dao.queryColumns("sys_city");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	@Override
	public String getFieldDesc(String tableName,String fieldName) {
		// TODO Auto-generated method stub
		return "";
	}
}
