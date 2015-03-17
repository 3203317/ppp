package cn.newcapec.framework.core.biz;

import cn.newcapec.framework.core.logs.LogEnabled;

public abstract interface BaseService<T> extends LogEnabled
{
  public abstract void removeUnused(String paramString);

  public abstract T get(String paramString);

  public abstract void saveOrUpdate(T paramT);
}