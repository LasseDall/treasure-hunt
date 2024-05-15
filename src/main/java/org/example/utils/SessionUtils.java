package org.example.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Component
public class SessionUtils {
    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    //CHANGE BEFORE PROD
    private SecretKeySpec keySpec = new SecretKeySpec("12345678910".getBytes(StandardCharsets.UTF_8), KEY_ALGORITHM);

    private byte[] encryptData(byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Encryption error", e);
        }
    }

    private byte[] decryptData(byte[] encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            return cipher.doFinal(encryptedData);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Encryption error", e);
        }
    }

    public void setEncryptedSessionAttribute(HttpServletRequest request, String attributeName, String attributeValue) throws Exception {
        try {
            HttpSession session = request.getSession();
            byte[] encryptedValue = encryptData(attributeValue.getBytes());
            String encryptedBase64Value = Base64.getEncoder().encodeToString(encryptedValue);
            session.setAttribute(attributeName, encryptedBase64Value);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Encryption error", e);
        }
    }

    public String getDecryptedSessionAttribute(HttpServletRequest request, String attributeName) throws Exception {
        try {
            HttpSession session = request.getSession(false);
            String encryptedBase64Value = (String) session.getAttribute(attributeName);
            byte[] encryptedValue = Base64.getDecoder().decode(encryptedBase64Value);
            byte[] decryptedValue = decryptData(encryptedValue);
            return new String(decryptedValue);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Encryption error", e);
        }
    }
}
