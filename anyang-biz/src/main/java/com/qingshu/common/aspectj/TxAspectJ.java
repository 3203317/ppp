package com.qingshu.common.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.qingshu.common.annotation.Transaction;

/**
 * 声明本类为一个切面
 */
@Aspect
public class TxAspectJ {
	@Autowired
	DataSourceTransactionManager transactionManager;
	TransactionStatus ts=null;
	/**
	 * 声明一个切入点（包括切入点表达式和切入点签名）
	 */
	@Pointcut("execution(* com.qingshu.base.mybatis.controller.*.*(..))")
	public void txMethod() {
	}
	
	/**
	 * 开启事务
	 */
	@Before("txMethod() && @annotation(tx)")
	public void beforeAdvide(JoinPoint jp,Transaction tx) {
		DefaultTransactionDefinition td=new DefaultTransactionDefinition();
		td.setIsolationLevel(tx.isolationLevel());
		ts=transactionManager.getTransaction(td);
		System.out.println("开启事务");
	}

	/**
	 * 事务回滚
	 */
	@AfterThrowing(pointcut = "txMethod() && @annotation(tx)", throwing = "throwing")
	public void rollback(JoinPoint point, RuntimeException throwing,Transaction tx) {
		transactionManager.rollback(ts);
		System.out.println("出现异常---事务回滚");
	}
	@AfterReturning("txMethod() && @annotation(tx)")
	public void commit(JoinPoint point,Transaction tx) {
		transactionManager.commit(ts);
		System.out.println("提交事务");
	}
}