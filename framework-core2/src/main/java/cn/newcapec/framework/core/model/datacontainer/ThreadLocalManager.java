package cn.newcapec.framework.core.model.datacontainer;

public abstract interface ThreadLocalManager {
	public abstract ThreadLocalContainer getFrameworkContainer();

	public abstract ThreadLocalContainer getAppFrameworkContainer();

	public abstract ThreadLocalContainer getAppContainer();

	public abstract void registerContainer(String paramString,
			ThreadLocalContainer paramThreadLocalContainer);

	public abstract void removeContainer(String paramString);
}