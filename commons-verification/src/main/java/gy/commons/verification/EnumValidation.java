package gy.commons.verification;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName EnumValidation.java
 * @Author guofeng
 * @Description
 * @Version 1.0.0
 * @Date 2020年05月09日 11:16:00
 */
public class EnumValidation implements ConstraintValidator<EnumValidAnnotation,Object> {

    Class<?> cls ; //枚举类
    String methodName;

    @Override
    public void initialize(EnumValidAnnotation constraintAnnotation) {
        cls=constraintAnnotation.value();
        methodName=constraintAnnotation.inputMethod();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (o==null){
                return true;
            }
            if (cls!=null && cls.isEnum()) {
                //枚举类验证
                Object[] objs = cls.getEnumConstants();
                Method method = cls.getMethod(methodName);
                for (Object obj : objs) {
                    Object code = method.invoke(obj, new Object[]{});
                    if (o.equals((Object) code)) {
                        return true;
                    }
                }
            }
            else {
                return true;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }
}
