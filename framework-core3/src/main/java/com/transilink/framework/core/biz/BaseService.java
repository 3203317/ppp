package com.transilink.framework.core.biz;

import com.transilink.framework.core.logs.LogEnabled;

public abstract interface BaseService<T> extends LogEnabled
{
  public abstract void removeUnused(String paramString);

  public abstract T get(String paramString);

  public abstract void saveOrUpdate(T paramT);
}