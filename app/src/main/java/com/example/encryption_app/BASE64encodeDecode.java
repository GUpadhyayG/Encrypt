package com.example.encryption_app;
import java.util.Base64;

public class BASE64encodeDecode {
    public static void main(String[] args) {
        // Sample string to encode
        String originalString = "Hello, World!";

        // Encode the string
        String encodedString = encodeToBase64(originalString);
        System.out.println("Encoded: " + encodedString);

        // Decode the string
        String decodedString = decodeFromBase64(encodedString);
        System.out.println("Decoded: " + decodedString);
    }
    // Method to encode a string to Base64
    public static String encodeToBase64(String input) {
        // Get bytes from the string
        byte[] encodedBytes = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encodedBytes = Base64.getEncoder().encode(input.getBytes());
        }
        // Convert bytes to string and return
        return new String(encodedBytes);
    }

    // Method to decode a Base64 encoded string
    public static String decodeFromBase64(String input) {
        // Get bytes from the Base64 encoded string
        byte[] decodedBytes = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            decodedBytes = Base64.getDecoder().decode(input);
        }
        // Convert bytes to string and return
        return new String(decodedBytes);
    }



}
