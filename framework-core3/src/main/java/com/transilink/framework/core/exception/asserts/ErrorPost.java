package com.transilink.framework.core.exception.asserts;

import com.transilink.framework.core.exception.BaseException;

public abstract interface ErrorPost {
	public abstract Object doInstancePost() throws BaseException;
}