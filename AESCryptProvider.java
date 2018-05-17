package com.citi.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESCryptProvider {

    private static String seed = "782569dfsdfsdredfdf";

    public static String generateClearText (String cipherText){

        // Define cipher text variable
        byte[] clearText = null;

        // Generate Key
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            keyGenerator.init(128, new SecureRandom(seed.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // key transformation
            Key key = new SecretKeySpec(keyBytes, "AES");

            // Encryption
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            clearText = cipher.doFinal(Misc.parseHexStr2Byte(cipherText));

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new String(clearText);
    }


    public static String generateCipherText (String clearText){

        // Define cipher text variable
        byte[] cipherText = null;

        // Generate Key
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            keyGenerator.init(128, new SecureRandom(seed.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // key transformation
            Key key = new SecretKeySpec(keyBytes, "AES");

            // Encryption
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(clearText.getBytes());

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Misc.parseByte2HexStr(cipherText);
    }

    public static void main(String[] args) {
        String pas = "Citigroup";
        String cipherText = AESCryptProvider.generateCipherText(pas);
        String clearText = AESCryptProvider.generateClearText(cipherText);
        System.out.println(cipherText);
        System.out.println(clearText);
    }
}
