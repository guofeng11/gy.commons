package gy.commons.security.jwt.auth0;

import com.auth0.jwt.algorithms.Algorithm;
import gy.commons.security.jwt.KeyUtil;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @ClassName JWTAlgorithm.java
 * @Author guofeng
 * @Description JWT算法接口 RSA、EC、HAMC
 * @Version 1.0.0
 * @Date 2020年06月11日 15:08:00
 */
public interface JWTAlgorithm {

    /**
     * 加密时 Algorithm
     * @param algorithmType
     * @param privateKey
     * @param hmacKey 非 HAMC 算法时 可为 null
     * @return
     */
    Algorithm encodeAlgorithm(KeyUtil.AlgorithmType algorithmType, PrivateKey privateKey, String hmacKey);
    /**
     * 解密时 Algorithm
     * @param algorithmType
     * @param publicKey
     * @param hmacKey 非 HAMC 算法时 可为 null
     * @return
     */
    Algorithm decodeAlgorithm(KeyUtil.AlgorithmType algorithmType, PublicKey publicKey, String hmacKey);
}
