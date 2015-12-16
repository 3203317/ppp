package com.qingshu.core.freemarker.config;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.JstlView;

import com.qingshu.common.util.ResourceUtil;

/**
 * 扩展spring的FreemarkerView，加上base属性。
 * 
 * 支持jsp标签，Application、Session、Request、RequestParameters属性
 * 
 * @author liufang
 * 
 */
public class RichJstlMarkerView extends JstlView {
	/**
	 * 部署路径属性名称
	 */
	public static final String CONTEXT_PATH = "base";
	public static final String Res_Name = "rname";
	/**
	 * 在model中增加部署路径base，方便处理部署路径问题。
	 */
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request)
			throws Exception {
		super.exposeModelAsRequestAttributes(model, request);
	}
	@Override
	protected void initServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		super.initServletContext(servletContext);
		servletContext.setAttribute(CONTEXT_PATH, servletContext.getContextPath());
		servletContext.setAttribute(Res_Name, ResourceUtil.getConfigByName("Res_Name"));
	}
	
}