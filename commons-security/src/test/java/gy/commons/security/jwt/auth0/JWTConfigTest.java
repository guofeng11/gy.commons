package gy.commons.security.jwt.auth0;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @ClassName JWTConfigTest.java
 * @Author guofeng
 * @Description
 * @Version 1.0.0
 * @Date 2020年06月10日 16:29:00
 */
@SpringBootTest(classes = JWTConfig.class,properties = "classpath:application.properties")

class JWTConfigTest {
   @Autowired
    private JWTConfig jwtConfig;

   @Test
   void  TestConfigValue(){
       System.out.println(jwtConfig.getAlgorithmType().getAlgorithmName());
       System.out.println(jwtConfig.getHmacKey());
       System.out.println(jwtConfig.getRsaPrivateKey());
       System.out.println(jwtConfig.getRsaPublicKey());
   }
}