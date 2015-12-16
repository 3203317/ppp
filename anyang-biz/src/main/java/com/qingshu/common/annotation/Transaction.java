package com.qingshu.common.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.transaction.TransactionDefinition;

/**
 * 事务注解
 */
@Target({ElementType.METHOD})     
@Retention(RetentionPolicy.RUNTIME)     
public @interface Transaction {
	/**
	 * 是否启用事务，默认启用
	 * @return
	 */
	boolean isOpen() default true;
	int isolationLevel() default TransactionDefinition.ISOLATION_DEFAULT;
}