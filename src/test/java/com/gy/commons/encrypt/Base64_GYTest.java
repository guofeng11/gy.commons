package com.gy.commons.encrypt;



import org.junit.Test;

/**
 * @Auther: guofeng
 * @Date: 2019/5/30 17:19
 * @Description:
 */
public class Base64_GYTest {

    @Test
    public void encryptBase64() {
        try {
            String eStr=  Base64_GY.encrypt("中国，您好");
            System.out.println(eStr);
            String dStr=Base64_GY.decrypt(eStr);
            System.out.println(dStr);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    @Test
    public void encryptDes() {
        try {
            String eStr=  DES.encrypt("中国，您好","234");
            System.out.println(eStr);
            String dStr=DES.decrypt(eStr,"234");
            System.out.println(dStr);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }
    @Test
    public void encryptDes3() {
        try {
            String eStr3=  DES3.encrypt("中国，您好","123");
            System.out.println(eStr3);
            String dStr3=DES3.decrypt(eStr3,"123");
            System.out.println(dStr3);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @Test
    public void encryptMD5() {
        try {
            String eStrmd5=  OneWay.encrypt("你好呀是素什锦是啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊1q+","qa",OneWay.Algorithm.MD5);
            System.out.println("md5    "+eStrmd5+"  "+ eStrmd5.length());
            String eStrSHA=  OneWay.encrypt("你好呀是素什锦是啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊1q+",OneWay.Algorithm.SHA);
            System.out.println("SHA    "+eStrSHA+"  "+ eStrSHA.length());
            String SHA256=  OneWay.encrypt("你好呀是素什锦是啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊1q+",OneWay.Algorithm.SHA256);
            System.out.println("SHA256 "+SHA256+"  "+ SHA256.length());
            String SHA384=  OneWay.encrypt("你好呀是素什锦是啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊1q+",OneWay.Algorithm.SHA384);
            System.out.println("SHA384 "+SHA384 +"  "+ SHA384.length());
            String SHA512=  OneWay.encrypt("你好呀是素什锦是啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊1q+",OneWay.Algorithm.SHA512);
            System.out.println("SHA512 "+SHA512 +"  "+SHA512.length());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
