package com.example.encryption_app;

import static com.example.encryption_app.BASE64encodeDecode.encodeToBase64;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.util.Base64;

public class Base64test {

    @Test
    public void testEncodeToBase64_normalString() {
        String input = "Hello, World!";
        String expectedOutput = Base64.getEncoder().encodeToString(input.getBytes());
        String actualOutput = encodeToBase64(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEncodeToBase64_emptyString() {
        String input = "";
        String expectedOutput = Base64.getEncoder().encodeToString(input.getBytes());
        String actualOutput = encodeToBase64(input);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEncodeToBase64_specialCharacters() {
        String input = "こんにちは"; // "Hello" in Japanese
        String expectedOutput = Base64.getEncoder().encodeToString(input.getBytes());
        String actualOutput = encodeToBase64(input);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEncodeToBase64_androidVersionCheck() {
        String input = "TestString";
        String actualOutput = encodeToBase64(input);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String expectedOutput = Base64.getEncoder().encodeToString(input.getBytes());
            assertEquals(expectedOutput, actualOutput);
        } else {
            assertEquals("", actualOutput); // Since encodedBytes is initialized to empty byte array
        }
    }

    private String encodeToBase64(String input) {
        if (input == null) {
            return null; // Return null for null input
        }
        byte[] encodedBytes = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encodedBytes = Base64.getEncoder().encode(input.getBytes());
        }
        return new String(encodedBytes);
    }

    @Test
    public void testDecodeFromBase64_validEncodedString() {
        String input = Base64.getEncoder().encodeToString("Hello, World!".getBytes());
        String expectedOutput = "Hello, World!";
        String actualOutput = decodeFromBase64(input);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testDecodeFromBase64_emptyString() {
        String input = "";
        String expectedOutput = "";
        String actualOutput = decodeFromBase64(input);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testDecodeFromBase64_nullInput() {
        String input = null;
        String actualOutput = decodeFromBase64(input);

        assertNull(actualOutput);
    }

    @Test
    public void testDecodeFromBase64_invalidEncodedString() {
        String input = "InvalidBase64String";
        try {
            decodeFromBase64(input);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Illegal base64 character"));
        }
    }

    @Test
    public void testDecodeFromBase64_specialCharacters() {
        String input = Base64.getEncoder().encodeToString("こんにちは".getBytes()); // "Hello" in Japanese
        String expectedOutput = "こんにちは";
        String actualOutput = decodeFromBase64(input);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testDecodeFromBase64_androidVersionCheck() {
        String input = Base64.getEncoder().encodeToString("TestString".getBytes());
        String actualOutput = decodeFromBase64(input);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String expectedOutput = "TestString";
            assertEquals(expectedOutput, actualOutput);
        } else {
            assertEquals("", actualOutput); // Since decodedBytes is initialized to an empty byte array
        }
    }

    private String decodeFromBase64(String input) {
        if (input == null) {
            return null; // Return null for null input
        }
        byte[] decodedBytes = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            decodedBytes = Base64.getDecoder().decode(input);
        }
        return new String(decodedBytes);
    }


}
