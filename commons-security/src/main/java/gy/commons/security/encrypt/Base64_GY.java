package gy.commons.security.encrypt;


import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @Auther: guofeng
 * @Date: 2019/5/30 17:02
 * @Description:
 */
public class Base64_GY {
    //字符串编码
    private static final    String charSet="utf-8";


    /**
     * baseb4 编码
     * @param str 编码字符串
     * @return 编码后的字符串
     * @throws UnsupportedEncodingException
     */
    public static String encrypt(String str) throws UnsupportedEncodingException {
        byte[] src=str.getBytes(charSet);
        return Base64.getEncoder().encodeToString(src);
    }
    public static String encrypt(byte[] buff){
        return Base64.getEncoder().encodeToString(buff);
    }

    /**
     * baseb4 编码
     * @param str 要编码的字符串
     * @return 编码后的字节数组
     * @throws UnsupportedEncodingException
     */
    public static byte[] encryptToByte(String str) throws UnsupportedEncodingException {
        byte[] src=str.getBytes(charSet);
        return Base64.getEncoder().encode(src);
    }
    /**
     * base64 解码
     * @param str 要解码的字符串
     * @return 解码后的字符串
     * @throws UnsupportedEncodingException
     */
    public static String decrypt(String str) throws UnsupportedEncodingException {
       byte[] src= Base64.getDecoder().decode(str);
       return  new String(src,charSet);
    }
    public static String decrypt(byte[] buff) throws UnsupportedEncodingException {
        byte[] src= Base64.getDecoder().decode(buff);
        return  new String(src,charSet);
    }
    public static byte[] decryptToByte(String str) {
        byte[] src= Base64.getDecoder().decode(str);
        return  src;
    }
}
