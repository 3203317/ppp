package com.qingshu.core.mybatis.plugins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qingshu.common.filters.FilterHandler;
import com.qingshu.common.filters.SearchUtil;
import com.qingshu.common.pager.PagerInfo;
import com.qingshu.common.util.ContextUtils;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.common.util.ReflectUtils;

/**
 * 查询分页拦截器,并加上分页的参数和qbc查询
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {

	private final Logger logger = LoggerFactory.getLogger(PaginationInterceptor.class);
	private String dialect = "";
	public Object intercept(Invocation ivk) throws Throwable {
		if (!(ivk.getTarget() instanceof RoutingStatementHandler)) {
			return ivk.proceed();
		}
		RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
		BaseStatementHandler delegate = (BaseStatementHandler) ReflectUtils.getValueByFieldName(statementHandler, "delegate");
		MappedStatement mappedStatement = (MappedStatement) ReflectUtils.getValueByFieldName(delegate, "mappedStatement");

		// BoundSql封装了sql语句
		BoundSql boundSql = delegate.getBoundSql();
		// 获得查询对象
		Object parameterObject = boundSql.getParameterObject();
		// 根据参数类型判断是否是分页方法
		if (!(parameterObject instanceof PagerInfo)) {
			return ivk.proceed();
		}
		logger.debug("开始拦截SQL语句");
		Connection connection = (Connection) ivk.getArgs()[0];
		String sql = boundSql.getSql();
		PagerInfo pagerInfo = (PagerInfo) parameterObject;
		FilterHandler filterHandler=pagerInfo.getFilterHandler();
		if(!ObjectUtils.isEmpty(filterHandler))
		{
			if(sql.indexOf("where")>-1)
			{
				SearchUtil.hasWhere="and";
			}
			sql+= SearchUtil.getCombOperation(filterHandler); 
		}
		// 获取查询数来的总数目
		String countSql = "SELECT COUNT(0) FROM (" + sql + ") tmp ";
		PreparedStatement countStmt = connection.prepareStatement(countSql);
		BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), parameterObject);
		setParameters(countStmt, mappedStatement, countBS, parameterObject);
		ResultSet rs = countStmt.executeQuery();
		int count = 0;
		if (rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		countStmt.close();

		// 设置总记录数
		pagerInfo.setTotalResult(count);
		// 设置总页数
		if(!pagerInfo.isPage)
		{
			pagerInfo.setPageSize(count);
		}
		else {
			pagerInfo.setTotalPage((count + pagerInfo.getPageSize() - 1) / pagerInfo.getPageSize());
			// 保存分页条到request
			ContextUtils.getRequest().setAttribute(PagerInfo.PAGERSTR, pagerInfo.getToolBar2());
			// 拼装查询参数
			sql = generatePageSql(sql, pagerInfo);
		}
		logger.debug("装配后SQL语句 : " + sql);
		//反射赋值SQL
		ReflectUtils.setValueByFieldName(boundSql, "sql", sql);
		
		return ivk.proceed();
	}

	/**
	 * 设置参数
	 */
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					@SuppressWarnings("unchecked")
					TypeHandler<Object> typeHandler = (TypeHandler<Object>) parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("参数不存在： " + propertyName + " of statement " + mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
				}
			}
		}
	}

	/**
	 * 生成Sql语句
	 * 
	 * @param sql
	 * @param page
	 * @return
	 */
	private String generatePageSql(String sql, PagerInfo pagerInfo) {
		if (pagerInfo != null && (dialect != null || !dialect.equals(""))) {
			StringBuffer pageSql = new StringBuffer();
			if ("mysql".equals(dialect)) {
				pageSql.append(sql);
				pageSql.append(" LIMIT " + pagerInfo.getCurrentResult() + "," + pagerInfo.getPageSize());
			} else if ("oracle".equals(dialect)) {
				pageSql.append("SELECT * FROM (SELECT t.*,ROWNUM r FROM (");
				pageSql.append(sql);
				pageSql.append(") t WHERE rownum <= ");
				pageSql.append(pagerInfo.getCurrentResult() + pagerInfo.getPageSize());
				pageSql.append(") WHERE r >");
				pageSql.append(pagerInfo.getCurrentResult());
			}
			return pageSql.toString();
		} else {
			return sql;
		}
	}

	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
		if (dialect == null || dialect.equals("")) {
			try {
				throw new PropertyException("数据库方言未设置");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
	}

}