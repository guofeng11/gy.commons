package com.gy.commons.encrypt;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.*;


/**
 * @Auther: guofeng
 * @Date: 2019/5/30 16:15
 * @Description:
 */
public class DES {
    //字符串编码
    private static final    String charSet="utf-8";

    /**
     * 转换密钥<br>
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static Key toKey(String key) throws  NoSuchAlgorithmException,
            UnsupportedEncodingException {
        //SecretKey 负责保存对称密钥
        SecretKey secretKey;
        //实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
        KeyGenerator  keygen = KeyGenerator.getInstance("DES");
        if (key==null || key.isEmpty()){
            //生成密钥
            secretKey= keygen.generateKey();
        }
        else{

            byte[] byteKEY=key.getBytes(charSet);
            SecureRandom secureRandom = new SecureRandom(byteKEY);
            keygen.init(secureRandom);
            secretKey = keygen.generateKey();

        }

        return secretKey;
    }
    /**
     * 对字符串加密
     *
     * @param str
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String encrypt(String str,String key) throws NoSuchPaddingException, NoSuchAlgorithmException,
             InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {

        //Cipher负责完成加密或解密工作,生成Cipher对象,指定其支持的DES算法
        Cipher c= Cipher.getInstance("DES");

        Key desKey=toKey(key);
        // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
        c.init(Cipher.ENCRYPT_MODE, desKey);

        byte[] src = str.getBytes(charSet);
        // 加密，结果保存进cipherByte
        byte[]  cipherByte = c.doFinal(src);
        return  Base64_GY.encrypt(cipherByte);
}

    /**
     * 对字符串解密
     *
     * @param str
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String decrypt(String str,String key) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException,
            UnsupportedEncodingException {
        // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
        //Cipher负责完成加密或解密工作,生成Cipher对象,指定其支持的DES算法
        Cipher c= Cipher.getInstance("DES");
        Key desKey=toKey(key);
        c.init(Cipher.DECRYPT_MODE, desKey);

        byte[] src =Base64_GY.decryptToByte(str);
        byte[]  cipherByte = c.doFinal(src);

        return new String(cipherByte,charSet);
    }
}
