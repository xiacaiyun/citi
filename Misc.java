package com.citi.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.regex.Pattern;

public class Misc {


    public static boolean isNumeric(String str) {
        return Misc.isNotBlank(str)
                && Pattern.compile("[0-9]*").matcher(str).matches();
    }


    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static <T> String join(String join, List<T> list) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i == (list.size() - 1)) {
                sb.append(list.get(i));
            } else {
                sb.append(list.get(i)).append(join);
            }
        }

        return new String(sb);
    }

    public static void directWriteFile(String filename, String content) throws Exception {
        try {
            File newFile = new File(filename);
            FileWriter writer = new FileWriter(newFile);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(content);
            bw.close();
            writer.close();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public static byte[] hexStringToByteArray(String strHexString) {
        if (Misc.isBlank(strHexString))
            return new byte[0];

        char[] arr = strHexString.toCharArray();

        int len = arr.length;
        if ((len % 2) != 0)
            throw new IllegalArgumentException("Error In hexStringToByteArray!");

        int byteLen = len / 2;
        byte[] bytes = new byte[byteLen];

        for (int i = 0; i < byteLen; i++) {
            bytes[i] = (byte) ((Character.digit(arr[i * 2], 16) << 4) + Character.digit(arr[i * 2 + 1], 16));
        }
        return bytes;
    }

    public static String byteArrayToHexString(byte[] bytes) {

        int blen;
        if ((bytes == null) || ((blen = bytes.length) == 0))
            return "";

        StringBuilder sb = new StringBuilder(2 * blen);
        for (int i = 0; i < blen; i++) {
            sb.append(String.format("%02x", bytes[i]));
        }

        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /** Change from Byte to Hex
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
}
