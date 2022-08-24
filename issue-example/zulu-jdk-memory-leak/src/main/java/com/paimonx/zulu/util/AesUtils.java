package com.paimonx.zulu.util;


import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author xu
 * @date 2022/8/24
 */
public class AesUtils {

    public static final String ALGORITHM = "AES";

    private AesUtils() {
    }

    public static String encrypt(String content, String key) {
        SecretKeySpec secretKeySpec = generateKey(key);
        return encrypt(content, secretKeySpec);
    }

    protected static String encrypt(String content, SecretKeySpec secretKeySpec) {
        try {
            Cipher encryptCipher = Cipher.getInstance(ALGORITHM);
            encryptCipher.init(1, secretKeySpec);
            byte[] valBytes = encryptCipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return new String(Hex.encodeHex(valBytes));
        } catch (Exception e) {
            return content;
        }
    }

    public static String decrypt(String content, String key) {
        SecretKeySpec secretKeySpec = generateKey(key);
        return decrypt(content, secretKeySpec);
    }

    protected static String decrypt(String content, SecretKeySpec secretKeySpec) {
        try {
            Cipher decryptCipher = Cipher.getInstance(ALGORITHM);
            decryptCipher.init(2, secretKeySpec);
            byte[] valBytes = decryptCipher.doFinal(Hex.decodeHex(content.toCharArray()));
            return new String(valBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return content;
        }
    }


    protected static SecretKeySpec generateKey(String key) {

        SecretKeySpec secretKeySpec = null;
        try {
            byte[] finalKey = new byte[16];
            int i = 0;
            byte[] var4 = key.getBytes(StandardCharsets.UTF_8);

            for (byte b : var4) {
                int var10001 = i++;
                finalKey[var10001 % 16] ^= b;
            }
            secretKeySpec = new SecretKeySpec(finalKey, ALGORITHM);
        } catch (Exception ignored) {
        }
        return secretKeySpec;
    }

    public static byte[] decryptByte(byte[] content, String key) {
        SecretKeySpec secretKeySpec = generateKey(key);
        return decryptByte(content, secretKeySpec);
    }

    public static byte[] encryptByte(byte[] content, String key) {
        SecretKeySpec secretKeySpec = generateKey(key);
        return encryptByte(content, secretKeySpec);
    }


    protected static byte[] encryptByte(byte[] content, SecretKeySpec secretKeySpec) {
        try {
            Cipher encryptCipher = Cipher.getInstance(ALGORITHM);
            encryptCipher.init(1, secretKeySpec);
            return encryptCipher.doFinal(content);
        } catch (Exception e) {
            return content;
        }
    }

    protected static byte[] decryptByte(byte[] content, SecretKeySpec secretKeySpec) {
        try {
            Cipher decryptCipher = Cipher.getInstance(ALGORITHM);
            decryptCipher.init(2, secretKeySpec);
            return decryptCipher.doFinal(content);
        } catch (Exception e) {
            return content;
        }
    }
}
