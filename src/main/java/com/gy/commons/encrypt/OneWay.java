package com.gy.commons.encrypt;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Auther: guofeng
 * @Date: 2019/5/31 16:03
 * @Description: 单向加密包含 MD5 SHA1
 */
public class OneWay {
    //字符串编码
    private static final    String charSet="utf-8";
    /**
     * 加密算法
     */
    public enum Algorithm{
        MD5("MD5"),SHA("SHA"),SHA256("SHA-256"),SHA384("SHA-384"),SHA512("SHA-512");

        /**
         * 加密算法名称
         * @return
         */
        public String getName() {
            return name;
        }

        private  String name;
        private Algorithm(String name){
            this.name=name;
        }
    }
    /**
     * 加密算法
     * @param str
     *  需要加密的字符串
     * @param algorithm
     * 加密算法名
     */
    public static String encrypt(String str, Algorithm algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance(algorithm.getName());
        byte[] src = str.getBytes(charSet);
        byte[] digest = md.digest(src);
        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, digest);
        // Convert message digest into hex value
        String hashtext = no.toString(16);

        // Add preceding 0s to make it 32 bit
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }

        // return the HashText
        return hashtext;
    }

    /**
     * 加密
     * @param str 要加密的字符
     * @param salt 盐
     * @param algorithm 加密算法
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String encrypt(String str,String salt, Algorithm algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance(algorithm.getName());
        byte[] src = str.getBytes(charSet);
        byte[] srcSalt = salt.getBytes(charSet);
        md.update(srcSalt);
        byte[] digest = md.digest(src);
        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, digest);
        // Convert message digest into hex value
        String hashtext = no.toString(16);

        // Add preceding 0s to make it 32 bit
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }

        // return the HashText
        return hashtext;
    }

}
