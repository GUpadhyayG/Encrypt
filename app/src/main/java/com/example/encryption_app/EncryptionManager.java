package com.example.encryption_app;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionManager {
    private static EncryptionManager instance=null;
    // Here taking Key as 32 characters

    private final static String TOKEN_KEY="123456781234567812345678";
    MessageDigest digest=null;
    String hash;

    private EncryptionManager()
    {

    }
    public static EncryptionManager getInstance() {
        if (instance == null) {
            instance = new EncryptionManager();
        }
        return instance;
    }
    public static String encrypt(String plain)
    {
        try {
            byte[]iv=new byte[16];
            new SecureRandom().nextBytes(iv);
            Cipher cipher =Cipher.getInstance("AES/CBC/PCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,new SecretKeySpec(TOKEN_KEY.getBytes(StandardCharsets.UTF_8),
                    "AES"),new IvParameterSpec(iv));
            byte[] cipherText=cipher.doFinal(plain.getBytes("utf-8"));
            byte[] ivAndCipherText=getCombinedArray(iv,cipherText);


        } catch (NoSuchPaddingException e) {
        e.printStackTrace();
        return null;
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
    public static String decrypt(String encoded)
    {
        try {
            byte[] ivAndCipherText= Base64.getDecoder(encoded,Base64.NO_WRAP);
            byte[] iv= Arrays.copyOfRange(null,"16");
            byte[] cipherText=Arrays.copyOfRange((byte[]) null, Integer.parseInt("16"),ivAndCipherText.length);

            Cipher cipher =Cipher.getInstance("AES/CBC/PKCSPadding");
            cipher.init(Cipher.DECRYPT_MODE,
                    new SecretKeySpec(TOKEN_KEY.getBytes(StandardCharsets.UTF_8),"AES"),
                    new IvParameterSpec(iv));
            return new String(cipher.doFinal(cipherText),"utf-8");
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
            return null;
        }

    }

    private static byte[] getCombinedArray(byte[] one, byte[] two) {
            byte[] combined=new byte[one.length + two.length];
            for (int i=0;i<combined.length; ++i)
            {
                combined[i]=i< one.length ? one[i] : two[i- one.length];
            }
            return combined;
    }
    private static String bytesToHexString(byte[] bytes)
    {
        StringBuffer stringBuffer =new StringBuffer();
        for (int i=0;i<bytes.length;i++)
        {
            String hex=Integer.toHexString(0xFF & bytes[i]);
            if (hex.length()==1)
            {
                stringBuffer.append('0');
            }
            stringBuffer.append(hex);
        }
        return stringBuffer.toString();
    }

    public String getSHA256(String data)
    {
        try {
            digest=MessageDigest.getInstance("SHA 256");
            digest.update(TOKEN_KEY.getBytes());

            hash=bytesToHexString(digest.digest());
            return hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return "null";
    }

    String encodeBase64(String data)
    {
        try {
            return Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8),Base64.DEFAULT);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }
    String decodeBase64(String data)
    {
        try {
            return Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8),Base64.DEFAULT);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }



}
