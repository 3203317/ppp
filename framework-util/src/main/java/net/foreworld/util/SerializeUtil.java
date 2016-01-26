package net.foreworld.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * SerializeUtil
 *
 * @author huangxin (3203317@qq.com)
 * @license LGPL (http://www.gnu.org/licenses/lgpl.html)
 * @copyright FOREWORLD.NET
 */
public class SerializeUtil {
	/**
	 * 序列化
	 *
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ByteArrayOutputStream baos = null;
		ObjectOutputStream out = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			out = new ObjectOutputStream(baos);
			out.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception ignore) {
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (Exception ignore) {
				}
			} // END
			if (null != baos) {
				try {
					baos.close();
				} catch (Exception ignore) {
				}
			}
		}
		return null;
	}

	/**
	 * 反序列化
	 *
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		ObjectInputStream in = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			in = new ObjectInputStream(bais);
			return in.readObject();
		} catch (Exception ignore) {
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (Exception ignore) {
				}
			} // END
			if (null != bais) {
				try {
					bais.close();
				} catch (Exception ignore) {
				}
			}
		}
		return null;
	}
}
