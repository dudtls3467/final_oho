package com.ohot.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtil {

	private static String key = "MySecretKey12345";
	private static String algorithm = "AES";
	
	public static String encrypt(String plainText) {
		
		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), algorithm);
			
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
			
			return Base64.getEncoder().encodeToString(encrypted);
			
		}catch(Exception e) {
			throw new RuntimeException("암호화 실패", e);
		}
	}
	
	public static String decrypt(String encryptedText) {
		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), algorithm);
			
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
			byte[] decrypted = cipher.doFinal(decodedBytes);
			
			return new String(decrypted, "UTF-8");
			
		}catch(Exception e) {
			throw new RuntimeException("복호화 실패", e);
		}
	}
}
