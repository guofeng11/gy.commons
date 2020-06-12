package gy.commons.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @ClassName UserToken.java
 * @Author guofeng
 * @Description 需要用户身份验证的API在请求参数中使用
 * @Version 1.0.0
 * @Date 2020年05月08日 13:57:00
 */
public class UserToken implements Serializable {
    private static final long serialVersionUID = 564417809532977349L;

    //用户标识
    @Min(value = 1,message = "{user.security.id}")
    private int userId;
    //用户令牌
    @NotEmpty(message = "{user.security.token}")
    private  String token;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserToken(){

    }

    public UserToken(int userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                '}';
    }
}
