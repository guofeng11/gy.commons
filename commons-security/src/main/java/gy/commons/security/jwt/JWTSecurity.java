package gy.commons.security.jwt;

import com.auth0.jwt.algorithms.Algorithm;

import java.time.LocalDateTime;


/**
 * @ClassName JWTSecurity.java
 * @Author guofeng
 * @Description
 * @Version 1.0.0
 * @Date 2020年06月08日 14:03:00
 */
public interface JWTSecurity {
    //创建令牌
    String createToken(CustomInfo customInfo, Algorithm algorithm) ;
    //验证令牌
    boolean checkToken(String token,Algorithm algorithm) ;
    //验证令牌 返回定义信息
    CustomInfo checkTokenOfCustomInfo(String token,Algorithm algorithm) ;
    //刷新令牌
    String refreshToken(String oldToken, Algorithm decodeAlgorithm, Algorithm encodeAlgorithm,LocalDateTime expiresLocalDateTime);
}
