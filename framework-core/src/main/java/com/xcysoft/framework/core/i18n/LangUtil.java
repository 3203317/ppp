package com.xcysoft.framework.core.i18n;

import com.xcysoft.framework.core.context.Keys;
import com.xcysoft.framework.core.context.NewcapecContext;
import com.xcysoft.framework.core.utils.stringUtils.GlobalVariant;
import com.xcysoft.framework.core.utils.stringUtils.StringUtil;

/**
 *
 * @author huangxin
 */
public class LangUtil {
	private LangUtil() {
	}

	public static String getLang(NewcapecContext context) {
		String lang = null;

		// get lang from request.parameter()
		lang = context.getParameter(Keys.LANG);
		lang = StringUtil.trim(lang);
		if (lang.length() > 0) {
			return lang;
		}

		// get lang from request.attribute()
		lang = (String) context.getAttribute(Keys.LANG);
		lang = StringUtil.trim(lang);
		if (lang.length() > 0) {
			return lang;
		}

		// get lang from session.user
		// LoginUser user = (LoginUser) (context.getAttribute(Keys.USER));
		// if (user != null) {
		// lang = user.getLang();
		// lang = StringUtil.trim(lang);
		// if (lang.length() > 0) {
		// return lang;
		// }
		// }

		// get lang from context
		lang = GlobalVariant.getVariant(Keys.LANG);
		lang = StringUtil.trim(lang);
		if (lang.length() > 0) {
			return Lang.ZH;
		}

		return Lang.ZH;
	}
}
