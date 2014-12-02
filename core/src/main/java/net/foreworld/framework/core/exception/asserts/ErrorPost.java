package net.foreworld.framework.core.exception.asserts;

import net.foreworld.framework.core.exception.BaseException;

/**
 *
 * @author huangxin
 *
 */
public interface ErrorPost {

	Object doInstancePost() throws BaseException;
}
