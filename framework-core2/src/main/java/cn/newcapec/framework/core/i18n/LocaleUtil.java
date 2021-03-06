package cn.newcapec.framework.core.i18n;

import cn.newcapec.framework.core.utils.stringUtils.GlobalVariant;
import java.util.Locale;

public final class LocaleUtil
{
  private static Locale loc = new Locale("zh", "", "");
  private static boolean inited = false;

  public static Locale getLocale()
  {
    return loc;
  }

  public static Locale getLocale(String lang)
  {
    return new Locale(lang, "", "");
  }

  private static void init() {
    if (!inited) {
      String lang = GlobalVariant.getVariant("lang");

      if (lang != null) {
        loc = new Locale(lang, "", "");
      }
    }
    inited = true;
  }

  static {
    init();
  }
}