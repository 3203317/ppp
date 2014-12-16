package cn.newcapec.framework.core.context;

public abstract class AbstractNewcapecContext extends NewcapecContext {
	public Object getAttribute(String s) {
		Object obj;
		if (((obj = getAttribute(1, s)) == null)
				&& ((obj = getAttribute(2, s)) == null)
				&& ((obj = getAttribute(5, s)) == null)) {
			obj = getAttribute(9, s);
		}
		return obj;
	}
}