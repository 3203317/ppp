package net.foreworld.dsession.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class SysCfgUtil {
	private static ResourceBundle config = null;
	private static final String FILE_NAME = "session";

	static {
		try {
			config = ResourceBundle.getBundle(FILE_NAME);
		} catch (Exception ignored) {
		}
	}

	/**
	 *
	 * @param key
	 *            根据关键字获取值
	 * @return 返回对应关键字的值
	 */
	public synchronized static String get(String key) {
		try {
			String _key = StringUtils.defaultIfEmpty(key, "");
			if (!config.containsKey(_key)) {
				return null;
			} // END
			String _str = config.getString(_key);
			_str = StringUtils.defaultIfEmpty(_str, "");
			_str = _str.trim();
			return _str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @param key
	 * @param returnVal
	 * @return
	 */
	public synchronized static String get(String key, String returnVal) {
		String result = get(key);
		return null == result ? returnVal : result;
	}

	public static Map<String, String> getAllConfig() {
		// 加载配置文件，以后需要移到配置表的service中
		Map<String, String> _map = new HashMap<String, String>();
		for (String key : config.keySet()) {
			_map.put(StringUtils.trim(key),
					StringUtils.trim(config.getString(key)));
		} // END
		return _map;
	}
}
