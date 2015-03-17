package com.transilink.framework.core.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UrlMapping
{
  public static Map<String, String> loadUrlMap = new ConcurrentHashMap();

  public static Map<String, String> loadFrameworkMap = new ConcurrentHashMap();
}