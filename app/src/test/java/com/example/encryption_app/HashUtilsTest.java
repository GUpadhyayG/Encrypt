package com.example.encryption_app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtilsTest {
    @Test
    public void testHashWithSHA256_normalString() {
        String input = "Hello, World!";
        String expectedOutput = hashWithSHA256Reference(input);
        String actualOutput = HashUtils.hashWithSHA256(input);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testHashWithSHA256_emptyString() {
        String input = "";
        String expectedOutput = hashWithSHA256Reference(input);
        String actualOutput = HashUtils.hashWithSHA256(input);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testHashWithSHA256_nullInput() {
        String input = null;
        String actualOutput = HashUtils.hashWithSHA256(input);

        assertNull(actualOutput);
    }

    @Test
    public void testHashWithSHA256_specialCharacters() {
        String input = "こんにちは"; // "Hello" in Japanese
        String expectedOutput = hashWithSHA256Reference(input);
        String actualOutput = HashUtils.hashWithSHA256(input);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testHashWithSHA256_consistency() {
        String input = "consistentInput";
        String hash1 = HashUtils.hashWithSHA256(input);
        String hash2 = HashUtils.hashWithSHA256(input);

        assertEquals(hash1, hash2);
    }

    @Test
    public void testHashWithSHA256_differentInputs() {
        String input1 = "input1";
        String input2 = "input2";
        String hash1 = HashUtils.hashWithSHA256(input1);
        String hash2 = HashUtils.hashWithSHA256(input2);

        assertNotEquals(hash1, hash2);
    }

    private String hashWithSHA256Reference(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Return null in case of error
        }
    }
}
