package gy.commons.verification;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @ClassName EnumValidAnnotation.java
 * @Author guofeng
 * @Description
 * @Version 1.0.0
 * @Date 2020年05月09日 11:15:00
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumValidation.class)
public @interface EnumValidAnnotation {
    Class<?> value();

    String inputMethod() default "name";

    String message() default "枚举类型错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
