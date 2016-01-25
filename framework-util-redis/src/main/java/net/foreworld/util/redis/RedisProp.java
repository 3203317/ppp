package net.foreworld.util.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import net.foreworld.util.StringUtil;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class RedisProp {
	private static ResourceBundle config = null;
	static {
		try {
			config = ResourceBundle.getBundle("redis");
		} catch (Exception ignore) {
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
			String _key = StringUtil.isEmpty(key, "");
			if (!config.containsKey(_key)) {
				return null;
			} // END
			String _str = config.getString(_key);
			_str = StringUtil.isEmpty(_str, "");
			return _str;
		} catch (Exception ignore) {
			ignore.printStackTrace();
		}
		return null;
	}

	public static Map<String, String> getAllConfig() {
		Map<String, String> _map = new HashMap<String, String>();
		for (String key : config.keySet()) {
			_map.put(StringUtil.isEmpty(key),
					StringUtil.isEmpty(config.getString(key)));
		} // END
		return _map;
	}
}
