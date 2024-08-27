package com.example.encryption_app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    // Static method to generate SHA-256 hash of the input string
    public static String hashWithSHA256(String input) {
        try {
            // Create a SHA-256 message digest
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());

            // Convert the byte array into a hex string
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
