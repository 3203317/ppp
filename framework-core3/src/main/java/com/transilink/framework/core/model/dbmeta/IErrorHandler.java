package com.transilink.framework.core.model.dbmeta;

public abstract interface IErrorHandler {
	public abstract void onError(String paramString, Throwable paramThrowable);
}