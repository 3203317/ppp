package test;

import junit.framework.TestCase;
import cn.newcapec.framework.core.rest.MetaData;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class Test extends TestCase {

	public void testApp() {
		MetaData md = new MetaData();
		System.out.println(md.toString());
	}
}
