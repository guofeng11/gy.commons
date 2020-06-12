package gy.commons.security.jwt.auth0;

import com.auth0.jwt.algorithms.Algorithm;
import gy.commons.security.jwt.CustomInfo;
import gy.commons.security.jwt.JWTSecurity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName JWTUtilTest.java
 * @Author guofeng
 * @Description
 * @Version 1.0.0
 * @Date 2020年06月09日 10:16:00
 */
@SpringBootTest(classes = {JWTUtil.class,JWTConfig.class})
class JWTUtilTest {

    @Autowired
    private JWTSecurity jwtSecurity;
    @Autowired
    private JWTAlgorithm jwtAlgorithm;
    @Autowired
    JWTConfig jwtConfig;
    @Test
    void createToken() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        jwtConfig.init();
        CustomInfo customInfo=new CustomInfo(LocalDateTime.now().plusYears(1),new HashMap(){{put("userId",10);}});
        CustomInfo customInfo1=new CustomInfo(LocalDateTime.now().plusDays(30),new HashMap(){{put("userId",11);}});
        String token=  jwtSecurity.createToken(customInfo,jwtAlgorithm.encodeAlgorithm(jwtConfig.getAlgorithmType(),jwtConfig.getPrivateKey(),jwtConfig.getHmacKey()));
        System.out.println(token);
        String token1=  jwtSecurity.createToken(customInfo1,jwtAlgorithm.encodeAlgorithm(jwtConfig.getAlgorithmType(),jwtConfig.getPrivateKey(),jwtConfig.getHmacKey()));
        System.out.println(token1);
    }

    @Test
    void  checkToken() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        jwtConfig.init();
        Algorithm algorithm=jwtAlgorithm.decodeAlgorithm(jwtConfig.getAlgorithmType(),jwtConfig.getPublicKey(),jwtConfig.getHmacKey());
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJFUzI1NiJ9.eyJhdWQiOiIiLCJzdWIiOiIiLCJuYmYiOjE1OTE4NjQyMjksImN1c3RvbSI6eyJ1c2VySWQiOjEwfSwiaXNzIjoiIiwiZXhwIjoxNjIzNDAwMjI5LCJpYXQiOjE1OTE4NjQyMjksImp0aSI6IiJ9.RNlslMvTZQVrvB4ESbyM2bUJYoUx-wQ2zbfeQ7U-LVyzwkU7PvqHkK_FNWsSfOs97U0JN1OnX7-29XwVtmcLHA";
        CustomInfo customInfo = jwtSecurity.checkTokenOfCustomInfo(token,algorithm);
    }
}