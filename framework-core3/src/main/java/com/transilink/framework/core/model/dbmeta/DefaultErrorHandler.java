package com.transilink.framework.core.model.dbmeta;

import com.transilink.framework.core.logs.LogEnabled;

public class DefaultErrorHandler implements IErrorHandler, LogEnabled {
	public void onError(String s, Throwable throwable) {
		log.error(throwable);
	}
}