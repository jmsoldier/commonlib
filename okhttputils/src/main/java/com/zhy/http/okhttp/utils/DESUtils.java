package com.zhy.http.okhttp.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 
 * @author shanpeng
 *
 */
public class DESUtils {
	private static final String DEFAULT_KEY = "e9D409f7cb25458dBD4894f3BD3d4C61";

	/**
	 * 使用默认密钥：e9D409f7cb25458dBD4894f3BD3d4C61，对字符串进行DES加密，返回BASE64编码的加密字符串
	 * 
	 * @param str
	 * @return
	 */

	/**
	 * 对字符串进行DES加密，返回BASE64编码的加密字符串
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @param keyStr
	 *            加密的密钥，不传时使用默认密钥：e9D409f7cb25458dBD4894f3BD3d4C61
	 * @return
	 */

	/**
	 * 使用默认密钥：e9D409f7cb25458dBD4894f3BD3d4C61，对加密对字符串进行DES解密，返回解密后的字符串
	 * 
	 * @param str
	 *            需要解密的加密字符串
	 * @return
	 */
	public static String decode(String str) {
		return decode(str, DEFAULT_KEY);
	}

	/**
	 * 对BASE64编码的加密字符串进行解密，返回解密后的字符串
	 * 
	 * @param str
	 *            需要解密的加密字符串
	 * @param keyStr
	 *            解密的密钥，需要和加密的密钥一致，不传时使用默认密钥：e9D409f7cb25458dBD4894f3BD3d4C61
	 * @return
	 */
	public static String decode(String str, String keyStr) {
		try {

			byte[] bytes = Base64.decode(str,Base64.DEFAULT);
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, getKey(keyStr));
			bytes = cipher.doFinal(bytes);
			return new String(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static Key getKey(String keyStr) {
		try {
			if (keyStr == null || "".equals(keyStr)) {
				keyStr = DEFAULT_KEY;
			}
			DESKeySpec objDesKeySpec = new DESKeySpec(keyStr.getBytes("UTF-8"));
			SecretKeyFactory objKeyFactory = SecretKeyFactory.getInstance("DES");
			return objKeyFactory.generateSecret(objDesKeySpec);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



//	public static void main(String[] args) {
//		String string = "{\"dd\":123123}";
//		String encodeString = encode(string);
//		System.out.println(encodeString);
//		System.out.println(decode(encodeString));
//		byte b = 127;
//		b = (byte) (b + 127);
//		System.out.println(b);
//		b = (byte) (b - 127);
//		System.out.println(b);
//		
//		System.out.println("==========simple==========");
//		encodeString = encodeSimple(string);
//		System.out.println(encodeString);
//		System.out.println(decodeSimple(encodeString));
//	}
}
