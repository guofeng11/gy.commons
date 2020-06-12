package gy.commons.security.jwt.auth0;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.*;
import gy.commons.security.jwt.CustomInfo;
import gy.commons.security.jwt.JWTSecurity;
import gy.commons.security.jwt.KeyUtil;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @ClassName JWTUtil.java
 * @Author guofeng
 * @Description
 * @Version 1.0.0
 * @Date 2020年06月08日 11:23:00
 */

@Component
public class JWTUtil implements JWTSecurity,JWTAlgorithm {

    /**
     * 生成令牌
     * @param customInfo
     * @param algorithm
     * @return
     */
    @Override
    public String createToken(CustomInfo customInfo,Algorithm algorithm) {

        String token = null;
        token = JWT.create()
                .withIssuer(customInfo.getIssuer())         //签发者
                .withAudience(customInfo.getAudience().split(","))     //观众，相当于接受者
                .withIssuedAt(CustomInfo.convertDate(customInfo.getIssuedAtDate()))   // 生成签名的时间
                .withExpiresAt(CustomInfo.convertDate(customInfo.getExpiresAtDate()))    // 生成签名的有效期
                .withClaim(CustomInfo.CLAIMKEY, customInfo.getCustomClaim()) //存数据
                .withSubject(customInfo.getSubject()) //所面向的用户
                .withNotBefore(CustomInfo.convertDate(customInfo.getNotBeforeDate()))  //生效时间
                .withJWTId(customInfo.getJWTId())   //编号
                .sign(algorithm);
        return token;
    }

    /**
     * 验证令牌
     * @param token
     * @param algorithm
     * @return 成功 true
     */
    @Override
    public boolean checkToken(String token,Algorithm algorithm) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (JWTVerificationException ex){
            return false;
        }

    }

    /**
     * 验证令牌 返回payload信息
     * @param token
     * @param algorithm
     * @return
     */
    @Override
    public CustomInfo checkTokenOfCustomInfo(String token, Algorithm algorithm) {
        try {
            JWTVerifier verifier = JWT.require(algorithm )
                    .build();
            DecodedJWT jwt = verifier.verify(token);

            CustomInfo customInfo = new CustomInfo(jwt.getIssuer(), String.join(",", jwt.getAudience()),
                    jwt.getIssuedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                    jwt.getExpiresAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                    jwt.getNotBefore().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                    jwt.getSubject(), jwt.getClaim(CustomInfo.CLAIMKEY).asMap(), jwt.getId());
            return customInfo;
        } catch (JWTVerificationException ex) {
            return null;
        }
    }

    /**
     * 刷新令牌
     * @param oldToken 有效的旧令牌
     * @param decodeAlgorithm 解密算法
     * @param encodeAlgorithm 加密算法
     * @param expiresLocalDateTime 新令牌过期时间
     * @return
     */
    @Override
    public String refreshToken(String oldToken, Algorithm decodeAlgorithm, Algorithm encodeAlgorithm,LocalDateTime expiresLocalDateTime) {
        String token = null;
        CustomInfo customInfo = checkTokenOfCustomInfo(oldToken,decodeAlgorithm);
        if (customInfo != null){
            customInfo.setExpiresAtDate(expiresLocalDateTime);
            token = createToken(customInfo,encodeAlgorithm);
        }
        return token;
    }

    /**
     * 加密时 Algorithm
     * @param algorithmType
     * @param privateKey
     * @param hmacKey 非 HAMC 算法时 可为 null
     * @return
     */
    public Algorithm encodeAlgorithm(KeyUtil.AlgorithmType algorithmType, PrivateKey privateKey,String hmacKey) {
        Algorithm algorithm = null;
        switch (algorithmType) {
            case HMAC:
                algorithm= Algorithm.HMAC256(hmacKey);
                break;
            case EC:
                algorithm = Algorithm.ECDSA256(null, (ECPrivateKey) privateKey);
                break;
            case RSA:
                algorithm = Algorithm.RSA256(null, (RSAPrivateKey) privateKey);
                break;
        }
        return algorithm;
    }
    /**
     * 解密时 Algorithm
     * @param algorithmType
     * @param publicKey
     * @param hmacKey 非 HAMC 算法时 可为 null
     * @return
     */
    public Algorithm decodeAlgorithm(KeyUtil.AlgorithmType algorithmType, PublicKey publicKey , String hmacKey){
        Algorithm algorithm = null;
        switch (algorithmType) {
            case HMAC:
                algorithm= Algorithm.HMAC256(hmacKey);
                break;
            case EC:
                algorithm = Algorithm.ECDSA256((ECPublicKey) publicKey, null);
                break;
            case RSA:
                algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, null);
                break;
        }
        return algorithm;
    }

}
