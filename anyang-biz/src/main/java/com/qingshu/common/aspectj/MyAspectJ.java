package com.qingshu.common.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.qingshu.common.plugin.log.LogAnnotation;
import com.qingshu.common.plugin.log.LogUtils;
import com.qingshu.core.mybatis.service.MbsCommonService;

/**
 * 声明本类为一个切面
 */
@Aspect
public class MyAspectJ {
	private AspectJModel aspectJModel;
	private LogUtils logUtils;

	/**
	 * 声明一个切入点（包括切入点表达式和切入点签名）
	 */
	@Pointcut("execution(* com.qingshu.core.mybatis.service..*.*(..))")
	// 切入类的拦截地址
	public void anyMethod() {
	}

	/**
	 * 声明一个前置通知
	 */
	@Before("anyMethod() && @annotation(log)")
	public void beforeAdvide(JoinPoint jp, LogAnnotation log) {
		this.aspectJModel = new AspectJModel(jp);
		this.logUtils = new LogUtils(aspectJModel, log);
		System.out.println("前置通知");
	}

	/**
	 * 声明一个后置通知
	 */
	@After("anyMethod() && @annotation(log)")
	public void afterAdvie(JoinPoint jp, LogAnnotation log) {
		logUtils.insertLog();
		System.out.println("后置通知:" + aspectJModel.getMethodName());
	}

	/**
	 * 声明一个异常通知
	 */
	@AfterThrowing(pointcut = "anyMethod()", throwing = "throwing")
	public void afterThrowsAdvice(JoinPoint point, RuntimeException throwing) {
		System.out.println("异常通知:" + throwing.getMessage());
	}

	@Autowired
	public MbsCommonService mbsCommonService;
}