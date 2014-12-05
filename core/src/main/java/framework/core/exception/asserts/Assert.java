package framework.core.exception.asserts;

import framework.core.exception.BaseException;

/**
 * 断言提示类
 *
 * @author huangxin
 *
 */
public class Assert {

	public static void isTrue(boolean exp, String message) {
		if (!exp) {
			throw new BaseException(message);
		}
	}
}
