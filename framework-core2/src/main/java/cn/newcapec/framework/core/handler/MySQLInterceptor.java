package cn.newcapec.framework.core.handler;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.EmptyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author huangxin
 *
 */
public class MySQLInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = -5182264670201560449L;

	@Autowired
	private HttpServletRequest request;

	@Override
	public String onPrepareStatement(String sql) {
		// HttpServletRequest request = ((ServletRequestAttributes)
		// RequestContextHolder.getRequestAttributes()).getRequest();

		String tenant_name = getTenantName();

		if (null != tenant_name) {
			String[] table_names = getDbTables();

			for (String table_name : table_names) {
				sql = sql.replaceAll("(?i) " + table_name + " ", " "
						+ tenant_name + "_" + table_name + " ");
			}
		}

		System.out.println("--==sql:" + sql);
		return super.onPrepareStatement(sql);
	}

	private String getTenantName() {
		return request.getParameter("tenant");
	}

	private String[] getDbTables() {
		String[] table_names = { "oa_article", "abc" };
		return table_names;
	}
}
