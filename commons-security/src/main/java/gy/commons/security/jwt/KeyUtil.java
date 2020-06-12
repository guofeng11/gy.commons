package gy.commons.security.jwt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName KeyUtil.java
 * @Author guofeng
 * @Description
 * @Version 1.0.0
 * @Date 2020年06月09日 14:20:00
 */
public class KeyUtil {

    //私钥键
    public static final   String KEY_PRIVATE="privateKey";
    //公钥健
    public static final  String KEY_PUBLIC="publicKEY";

    /**
     * 生成公钥私钥
     * @param algorithmType
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static Map<String,?> generatorKey(AlgorithmType algorithmType) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator =KeyPairGenerator.getInstance(algorithmType.getAlgorithmName());
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        Map<String,Key>  keyMap= new HashMap<>(2);
        keyMap.put(KEY_PRIVATE,keyPair.getPrivate());
        keyMap.put(KEY_PUBLIC,keyPair.getPublic());
        return keyMap;
    }

    /**
     *  合法公钥字符串转换为公钥
     * @param key
     * @param algorithmType
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey generatorPublicKey(String key ,AlgorithmType algorithmType) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithmType.getAlgorithmName());
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 合法私钥字符串转换为私钥
     * @param key
     * @param algorithmType
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey generatorPrivateKey(String key,AlgorithmType algorithmType) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithmType.getAlgorithmName());
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 公钥字符串
     * @param publicKey
     * @return
     */
    public static String publicKey2String(PublicKey publicKey){
        String publicKeyStr = (new BASE64Encoder()).encode(publicKey.getEncoded());
        return publicKeyStr;
    }

    /**
     * 加密算法
     * HMAC 密钥加密
     * RSA EC 密钥对非对称加密
     */
    public enum AlgorithmType{
        RSA("RSA"),EC("EC"),HMAC("HMAC");

        private String algorithmName;
        private AlgorithmType(String AlgorithmName){
            this.algorithmName = AlgorithmName;
        }

        public String getAlgorithmName() {
            return algorithmName;
        }
    }
}
