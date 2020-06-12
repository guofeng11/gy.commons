package gy.commons.security.jwt;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CustomInfo.java
 * @Author guofeng
 * @Description
 * @Version 1.0.0
 * @Date 2020年06月09日 09:37:00
 */

public class CustomInfo {

    //Claim key
    public static final String CLAIMKEY="custom";
    private String issuer;
    private String audience;
    private LocalDateTime IssuedAtDate;
    private LocalDateTime ExpiresAtDate;
    private LocalDateTime notBeforeDate;
    private String subject;
    private Map<String,?> CustomClaim;
    private String JWTId;

    private  LocalDateTime _localDateTime=LocalDateTime.now();
    public CustomInfo() {
        this(null,new HashMap());
        ExpiresAtDate = _localDateTime.plusMinutes(30);
        IssuedAtDate = _localDateTime;
        notBeforeDate = _localDateTime;
    }

    /**
     *
     * @param issuer 默认空
     * @param audience 默认空
     * @param issuedAtDate 默认当前时间
     * @param expiresAtDate  默认30分钟
     * @param notbeforeDate 默认当前时间
     * @param subject  默认空
     * @param customClaim  默认空
     * @param JWTId  默认空
     */
    public CustomInfo(String issuer, String audience, LocalDateTime issuedAtDate, LocalDateTime expiresAtDate, LocalDateTime notbeforeDate, String subject, Map<String,?> customClaim, String JWTId) {
        this.issuer = issuer;
        this.audience = audience;
        IssuedAtDate = issuedAtDate;
        ExpiresAtDate = expiresAtDate;
        notBeforeDate =notbeforeDate;
        this.subject = subject;
        CustomClaim = customClaim;
        this.JWTId = JWTId;
    }

    public CustomInfo(LocalDateTime expiresAtDate, Map customClaim) {
        this("","",null,expiresAtDate,null,"",customClaim,"");
        IssuedAtDate = _localDateTime;
        notBeforeDate = _localDateTime;
    }



    public LocalDateTime getExpiresAtDate() {
        return ExpiresAtDate;
    }

    public void setExpiresAtDate(LocalDateTime expiresAtDate) {
        ExpiresAtDate = expiresAtDate;
    }

    public Map getCustomClaim() {
        return CustomClaim;
    }

    public void setCustomClaim(Map customClaim) {
        CustomClaim = customClaim;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getAudience() {
        return audience;
    }

    public LocalDateTime getIssuedAtDate() {
        return IssuedAtDate;
    }

    public LocalDateTime getNotBeforeDate() {
        return notBeforeDate;
    }

    public String getSubject() {
        return subject;
    }

    public String getJWTId() {
        return JWTId;
    }

    public static  Date convertDate(LocalDateTime dateTime){
        if (dateTime == null){
            return  null;
        }
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
