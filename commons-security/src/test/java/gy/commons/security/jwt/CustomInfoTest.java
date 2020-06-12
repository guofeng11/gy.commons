package gy.commons.security.jwt;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName CustomInfoTest.java
 * @Author guofeng
 * @Description
 * @Version 1.0.0
 * @Date 2020年06月09日 09:56:00
 */
class CustomInfoTest {

    @Test
    void convertDate() {
        LocalDateTime localDateTime=LocalDateTime.now();
        System.out.println(localDateTime.toString());
        CustomInfo customInfo =new CustomInfo();
        Date date=customInfo.convertDate(localDateTime);
        System.out.println(date.toString());
    }
}