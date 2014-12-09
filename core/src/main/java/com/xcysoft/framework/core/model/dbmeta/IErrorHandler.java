package com.xcysoft.framework.core.model.dbmeta;

/**
 * 错误处理接口
 *
 * @author huangxin
 */
public interface IErrorHandler {

	public abstract void onError(String s, Throwable throwable);
}
