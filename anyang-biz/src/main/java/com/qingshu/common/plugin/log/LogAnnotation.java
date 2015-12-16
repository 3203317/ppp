package com.qingshu.common.plugin.log;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解
 */
@Target({ElementType.METHOD,ElementType.FIELD})     
@Retention(RetentionPolicy.RUNTIME)     
public @interface LogAnnotation {
	/**
	 * 操作模型
	 * @return
	 */
	String optModel() default "";
	/**
	 * 操作方法
	 * @return
	 */
	String optFunc() default "";
	/**
	 * 操作描述
	 * @return
	 */
    String logDesc();
}