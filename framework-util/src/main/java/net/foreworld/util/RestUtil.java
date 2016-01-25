package net.foreworld.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class RestUtil {

	private static final String ALGORITHM = "HmacSHA1";
	private static final String ENC = "UTF-8";

	public static String genSignature(String data, String key) {
		byte[] byteHMAC = null;
		try {
			Mac mac = Mac.getInstance(ALGORITHM);
			SecretKeySpec spec = new SecretKeySpec(key.getBytes(), ALGORITHM);
			mac.init(spec);
			byteHMAC = mac.doFinal(data.toLowerCase(Locale.getDefault())
					.getBytes());
		} catch (InvalidKeyException ignore) {
			return null;
		} catch (NoSuchAlgorithmException ignore) {
			return null;
		} // END

		String code = new BASE64Encoder().encode(byteHMAC);

		try {
			return URLEncoder.encode(code, ENC);
		} catch (UnsupportedEncodingException ignore) {
		}
		return null;
	}
}