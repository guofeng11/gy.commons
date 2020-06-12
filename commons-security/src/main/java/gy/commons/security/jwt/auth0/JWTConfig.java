package gy.commons.security.jwt.auth0;

import gy.commons.security.jwt.KeyUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName JWTConfig.java
 * @Author guofeng
 * @Description
 * @Version 1.0.0
 * @Date 2020年06月08日 11:22:00
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "mall.commons.security")
public class JWTConfig {

    //算法名称
    private KeyUtil.AlgorithmType algorithmType;
    //hmac 密钥
    private String hmacKey;
    //rsa/ec 私钥
    private String rsaPrivateKey;
    //rsa/ec 公钥
    private String rsaPublicKey;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * 非对称加密获取 密钥对
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws InvalidKeySpecException
     */
    public void init() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        switch (algorithmType){
            case HMAC:
                if (hmacKey == null && hmacKey.equals("")){
                    hmacKey= UUID.randomUUID().toString().replace("-","");
                }
                break;
            case RSA:
            case EC:
                if ((this.rsaPrivateKey == null || this.rsaPrivateKey.equals("")) &&
                        (this.rsaPublicKey == null ||  this.rsaPublicKey.equals(""))){
                    Map<String,?> mapKey = KeyUtil.generatorKey(algorithmType);
                    privateKey= (PrivateKey)mapKey.get(KeyUtil.KEY_PRIVATE);
                    publicKey = (PublicKey)mapKey.get(KeyUtil.KEY_PUBLIC);
                    BASE64Encoder base64Encoder =new BASE64Encoder();
                    this.rsaPublicKey = base64Encoder.encode(publicKey.getEncoded());
                    this.rsaPrivateKey = base64Encoder.encode(privateKey.getEncoded());
                }
                else{
                    if(rsaPrivateKey!=null && !rsaPrivateKey.equals("")){
                        privateKey = KeyUtil.generatorPrivateKey(rsaPrivateKey,algorithmType);
                    }
                    if(rsaPublicKey!=null && !rsaPublicKey.equals("")){
                        publicKey = KeyUtil.generatorPublicKey(rsaPublicKey,algorithmType);
                    }

                }
                break;
        }
    }

    public KeyUtil.AlgorithmType getAlgorithmType() {
        return algorithmType;
    }

    public void setAlgorithmType(KeyUtil.AlgorithmType algorithmType) {
        this.algorithmType = algorithmType;
    }

    public String getHmacKey() {
        return hmacKey;
    }

    public void setHmacKey(String hmacKey) {
        this.hmacKey = hmacKey;
    }

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    public String getRsaPublicKey() {
        return rsaPublicKey;
    }

    public void setRsaPublicKey(String rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }

    @Bean
    @ConfigurationPropertiesBinding
    public AlgorithmTypeConverter algorithmTypeConverter(){
        return  new AlgorithmTypeConverter();
    }

}


