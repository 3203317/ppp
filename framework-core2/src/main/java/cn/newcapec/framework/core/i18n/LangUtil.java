package cn.newcapec.framework.core.i18n;

import cn.newcapec.framework.core.context.NewcapecContext;
import cn.newcapec.framework.core.utils.stringUtils.GlobalVariant;
import cn.newcapec.framework.core.utils.stringUtils.StringUtil;

public class LangUtil {
	public static String getLang(NewcapecContext context) {
		String lang = null;

		lang = context.getParameter("lang");
		lang = StringUtil.trim(lang);
		if (lang.length() > 0) {
			return lang;
		}

		lang = (String) context.getAttribute("lang");
		lang = StringUtil.trim(lang);
		if (lang.length() > 0) {
			return lang;
		}

		lang = GlobalVariant.getVariant("lang");
		lang = StringUtil.trim(lang);
		if (lang.length() > 0) {
			return "zh";
		}

		return "zh";
	}
}