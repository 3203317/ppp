package cn.newcapec.framework.core.exception.asserts;

import cn.newcapec.framework.core.exception.BaseException;

public class Assert {
	public static void isTrue(boolean exp, String message) {
		if (!exp)
			throw new BaseException(message);
	}
}