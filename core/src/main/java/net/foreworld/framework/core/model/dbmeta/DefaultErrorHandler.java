package net.foreworld.framework.core.model.dbmeta;

import net.foreworld.framework.core.logs.LogEnabled;

/**
 * 默认错误处理
 *
 * @author huangxin
 */
public class DefaultErrorHandler implements IErrorHandler, LogEnabled {

	public void onError(String s, Throwable throwable) {
		log.error(throwable);

	}

}