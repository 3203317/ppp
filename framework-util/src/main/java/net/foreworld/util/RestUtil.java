package net.foreworld.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class RestUtil {

	public static String genSignature(String data, String key) {
		byte[] byteHMAC = null;
		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec spec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
			mac.init(spec);
			byteHMAC = mac.doFinal(data.toLowerCase(Locale.getDefault())
					.getBytes());
		} catch (InvalidKeyException ignore) {
			return null;
		} catch (NoSuchAlgorithmException ignore) {
			return null;
		}
		// TODO
		String oauth = new BASE64Encoder().encode(byteHMAC);

		try {
			return URLEncoder.encode(oauth, "UTF-8");
		} catch (UnsupportedEncodingException ignore) {
		}
		return null;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			System.out.println(genSignature((new Random()).toString(),
					(new Random()).toString()));
	}
}
