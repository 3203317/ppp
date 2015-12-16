package com.qingshu.core.mybatis.extend;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public  class UUIDTypeHandler extends BaseTypeHandler<Object> {

	@Override
	public Object getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return UUID.fromString(rs.getString(columnName));
	}

	@Override
	public Object getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return UUID.fromString((cs.getString(columnIndex)));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			Object parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, ((UUID) parameter).toString());
	}

	@Override
	public Object getNullableResult(ResultSet rs, int arg1) throws SQLException {
		return UUID.fromString(rs.getString(arg1));
	}
}
