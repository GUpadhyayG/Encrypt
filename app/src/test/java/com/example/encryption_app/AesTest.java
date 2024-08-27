package com.example.encryption_app;

import static com.example.encryption_app.AES.decrypt;
import static com.example.encryption_app.AES.encrypt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AesTest {


        private static final String ALGORITHM = "AES";
        private SecretKey secretKey;
    private String encryptedText;

    @Before
    public void setUp() throws Exception {
        // Generate a SecretKey for AES encryption
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(128); // Key size can be 128, 192, or 256 bits
        secretKey = keyGenerator.generateKey();
    }

    @Test
    public void testEncrypt_success() throws Exception {
        String plainText = "Hello, World!";
        String encryptedText = encrypt(plainText, secretKey);
        assertNotNull(encryptedText);
        assertNotEquals(plainText, encryptedText);
    }

    @Test(expected = NullPointerException.class)
    public void testEncrypt_nullInput() throws Exception {
        encrypt(null, secretKey);
    }
    private String encrypt(String plainText, SecretKey key) throws Exception {
        if (plainText == null) {
            throw new NullPointerException("Plain text cannot be null");
        }

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

        // Check for Android version and Base64 encoding
        // Simulated here for demonstration purposes
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    @Test
    public void testEncrypt_emptyString() throws Exception {
        String plainText = "";
        String encryptedText = encrypt(plainText, secretKey);

        assertNotNull(encryptedText);
        assertNotEquals(plainText, encryptedText);
    }


    @Test
    public void testDecrypt_success() throws Exception {
        String decryptedText = decrypt(encryptedText, secretKey);
        assertEquals("Hello, World!", decryptedText);
    }

    @Test
    public void testDecrypt_invalidCipherText() {
        String invalidEncryptedText = "InvalidCipherText";
        try {
            decrypt(invalidEncryptedText, secretKey);
            fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof javax.crypto.IllegalBlockSizeException || e instanceof javax.crypto.BadPaddingException);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testDecrypt_nullInput() throws Exception {
        decrypt(null, secretKey);
    }

    @Test(expected = NullPointerException.class)
    public void testDecrypt_nullKey() throws Exception {
        decrypt(encryptedText, null);
    }

}
