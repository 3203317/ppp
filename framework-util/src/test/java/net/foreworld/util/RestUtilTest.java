package net.foreworld.util;

import java.util.Random;

import junit.framework.TestCase;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class RestUtilTest extends TestCase {

	public void testGen() {
		for (int i = 0; i < 10; i++)
			System.out.println(RestUtil.genSignature((new Random()).toString(),
					(new Random()).toString()));
	}
}
