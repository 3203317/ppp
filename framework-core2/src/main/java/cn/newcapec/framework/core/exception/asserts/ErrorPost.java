package cn.newcapec.framework.core.exception.asserts;

import cn.newcapec.framework.core.exception.BaseException;

public abstract interface ErrorPost {
	public abstract Object doInstancePost() throws BaseException;
}