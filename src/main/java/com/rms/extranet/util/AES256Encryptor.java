package com.rms.extranet.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES256Encryptor {

    public static String encryptPassword(String plainPassword, String base64EncodedKey) throws Exception {
        // Decode the base64 encoded key
        byte[] keyByteArray = Base64.getDecoder().decode(base64EncodedKey.getBytes("UTF-8"));

        // Create secret key
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyByteArray, "AES");

        // Create cipher instance
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        // Initialize cipher in ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        // Encrypt the password
        byte[] encryptedBytes = cipher.doFinal(plainPassword.getBytes());

        // Encode the encrypted bytes to Base64 and return
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}
