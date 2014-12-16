package cn.newcapec.framework.core.rest;

import org.restlet.Context;
import org.restlet.data.Request;
import org.restlet.data.Response;

public abstract interface BaseResourceHandler
{
  public abstract void beforeHandle(Context paramContext, Request paramRequest, Response paramResponse);

  public abstract void afterHandle(Context paramContext, Request paramRequest, Response paramResponse);
}