package net.foreworld.dsession;

/**
 * 分布式Session
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public interface HttpSession {
	/**
	 * 设置Session
	 *
	 * @param name
	 * @param value
	 */
	void setAttribute(String name, Object value);

	/**
	 * 释放Session
	 */
	void invalidate();

	/**
	 * 获取Session
	 *
	 * @param name
	 * @return
	 */
	Object getAttribute(String name);
}