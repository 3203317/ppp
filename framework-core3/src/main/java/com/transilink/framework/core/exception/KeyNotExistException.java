package com.transilink.framework.core.exception;

public class KeyNotExistException extends BaseException
{
  public KeyNotExistException(Object key)
  {
    super("Key \"" + key + "\" does not exist!");
  }
}