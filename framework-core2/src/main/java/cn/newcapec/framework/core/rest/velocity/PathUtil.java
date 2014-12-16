package cn.newcapec.framework.core.rest.velocity;

import java.net.URL;

public class PathUtil
{
  private static String rootPath;

  public static String getRootPath()
  {
    return rootPath;
  }

  static
  {
    String classPath = PathUtil.class.getResource("/").getPath();

    if (classPath.indexOf("/./") != -1) {
      classPath = classPath.replaceAll("/./", "/");
    }
    rootPath = classPath.substring(0, classPath.indexOf("WEB-INF"));
  }
}