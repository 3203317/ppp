package com.qingshu.common.aspectj;

import org.aspectj.lang.JoinPoint;

/**
 * 切面辅助模型类
 */
public class AspectJModel {
	private Object[] args;/* 参数数组 */
	private String targetClassName;/*代理类名称*/
	private String methodName;/* 调用方法名 */

	public AspectJModel(JoinPoint joinPoint) {
		init(joinPoint);
	}
	public String getTargetClassName() {
		return targetClassName;
	}

	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * 初始化
	 */
	private void init(JoinPoint jp) {
		this.args = jp.getArgs();
		this.methodName = jp.getSignature().getName();
	}
}
