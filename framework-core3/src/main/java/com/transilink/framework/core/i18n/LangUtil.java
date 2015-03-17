package com.transilink.framework.core.i18n;

import com.transilink.framework.core.context.NewcapecContext;
import com.transilink.framework.core.utils.stringUtils.GlobalVariant;
import com.transilink.framework.core.utils.stringUtils.StringUtil;

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