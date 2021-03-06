package cn.newcapec.framework.core.context;

public abstract class NewcapecContext {
	public static final int REQUEST = 1;
	public static final int SESSION = 5;
	public static final int APPLICATION = 9;
	private static ThreadLocal cache = new ThreadLocal();

	public static void registerContext(NewcapecContext context) {
		cache.set(context);
	}

	public static synchronized NewcapecContext getContext() {
		Object obj;
		if ((obj = (NewcapecContext) cache.get()) == null) {
			registerContext((NewcapecContext) (obj = new DynaNewcapecContext()));
		}
		return (NewcapecContext) (NewcapecContext) obj;
	}

	public static void unregisterContext() {
		cache.set(null);
	}

	public abstract String getParameter(String paramString);

	public abstract String[] getParameters(String paramString);

	public abstract Object getAttribute(String paramString);

	public abstract Object getAttribute(int paramInt, String paramString);

	public abstract void setAttribute(int paramInt, String paramString,
			Object paramObject);

	public abstract void removeAttribute(int paramInt, String paramString);
}