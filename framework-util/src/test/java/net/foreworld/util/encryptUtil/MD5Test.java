package net.foreworld.util.encryptUtil;

import junit.framework.TestCase;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class MD5Test extends TestCase {

	public void testGen() {
		System.out.println(MD5.encode("123456"));
	}
}
