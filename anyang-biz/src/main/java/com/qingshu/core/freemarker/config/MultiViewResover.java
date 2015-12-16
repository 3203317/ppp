package com.qingshu.core.freemarker.config;

import java.util.Locale;
import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.qingshu.common.util.ResourceUtil;

/**
 * 多视图解析器
 */
public class MultiViewResover implements ViewResolver {

	private Map<String, ViewResolver> resolvers;
	private Integer order;

	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		int n = viewName.lastIndexOf("_"); // 获取viewName(modelAndView中的名字)看其有没有下划线									
		String rname=ResourceUtil.getConfigByName("Res_Name")+"/";//页面资源父路径
		if (n == (-1))
		{
			viewName=viewName+"_html";
			n = viewName.lastIndexOf("_"); 
		}
		// 有的话截取下划线后面的字符串 这里一般是jsp,ftl,vm与配置文件中的<entry key="ftl">的key匹配
		String suffix = viewName.substring(n + 1);
		// 根据下划线后面的字符串去获取托管的视图解析类对象
		ViewResolver resolver = resolvers.get(suffix);

		// 取下划线前面的部分 那时真正的资源名.比如我们要使用hello.jsp 那viewName就应该是hello_jsp
		viewName = viewName.substring(0, n);
		if (resolver != null)
		{
			return resolver.resolveViewName(rname+viewName, locale);
		}
		else {
			return null;
		}
		
	}

	public Integer getOrder() {
		return order;
	}


	public void setOrder(Integer order) {
		this.order = order;
	}


	public Map<String, ViewResolver> getResolvers() {
		return resolvers;
	}

	public void setResolvers(Map<String, ViewResolver> resolvers) {
		this.resolvers = resolvers;
	}
}
