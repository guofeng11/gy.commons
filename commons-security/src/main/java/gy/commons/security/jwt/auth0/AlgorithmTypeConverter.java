package gy.commons.security.jwt.auth0;

import gy.commons.security.jwt.KeyUtil;
import org.springframework.core.convert.converter.Converter;

/**
 * @ClassName AlgorithmTypeConverter.java
 * @Author guofeng
 * @Description
 * @Version 1.0.0
 * @Date 2020年06月11日 11:25:00
 */

public class AlgorithmTypeConverter implements Converter<String,KeyUtil.AlgorithmType> {

    @Override
    public KeyUtil.AlgorithmType convert(String s) {
            switch (s) {
                case "hmac":
                    return KeyUtil.AlgorithmType.HMAC;
                case "rsa":
                    return KeyUtil.AlgorithmType.RSA;
                case "ec":
                    return KeyUtil.AlgorithmType.EC;
                default:
                    return KeyUtil.AlgorithmType.HMAC;

            }
    }
}
