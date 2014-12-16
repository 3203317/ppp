package cn.newcapec.framework.core.model.dbmeta;

import cn.newcapec.framework.core.logs.LogEnabled;

public class DefaultErrorHandler implements IErrorHandler, LogEnabled {
	public void onError(String s, Throwable throwable) {
		log.error(throwable);
	}
}