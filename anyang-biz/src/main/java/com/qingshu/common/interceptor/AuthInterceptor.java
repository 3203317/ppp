package com.qingshu.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qingshu.base.pojo.JSUser;
import com.qingshu.common.json.model.SessionInfo;
import com.qingshu.common.plugin.log.LogAnnotation;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.core.springmvc.authority.FireAuthority;


/**
 * 权限拦截器
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	private final static Logger log= Logger.getLogger(AuthInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod method = (HandlerMethod)handler;
		//权限拦截注解器
		FireAuthority  fireAuthority = method.getMethod().getAnnotation(FireAuthority.class);
		if( fireAuthority == null || fireAuthority.verifyLogin()){
			JSUser jUser=SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
			if(ObjectUtils.isEmpty(jUser)||ObjectUtils.isEmpty(jUser.getId()))
			{
				String baseUri = request.getContextPath();
				String uri = request.getRequestURI();
				//session中没有用户信息但含有/admin/shopmapOther/ 的不拦截
				if(uri.contains("/admin/shopmapOther/")){
					return super.preHandle(request, response, handler);
				}else{
					//response.sendRedirect(baseUri+"/admin/login.do");
					response.getWriter().println("<script>window.top.location='"+baseUri+"/admin/login.do"+"'</script>");
				    return false;
				}
			}
		}
		return super.preHandle(request, response, handler);
	}
	// 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, 
			Object handler,ModelAndView modelAndView) throws Exception {
		System.out.println("CommonInterceptor拦截器: postHandle");
		HandlerMethod method = (HandlerMethod)handler;
		//权限拦截注解器
		LogAnnotation  logAnnotation = method.getMethod().getAnnotation(LogAnnotation.class);
		if( logAnnotation != null){
			//String optDesc=logAnnotation.optDesc();
		}
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		HandlerMethod method = (HandlerMethod)handler;
		//日志注解器
		LogAnnotation  logAnnotation = method.getMethod().getAnnotation(LogAnnotation.class);
		if( logAnnotation != null){
			//String optDesc=logAnnotation.optDesc();
			
		}
		System.out.println("CommonInterceptor拦截器: afterCompletion");
		
	}
	


	
}
