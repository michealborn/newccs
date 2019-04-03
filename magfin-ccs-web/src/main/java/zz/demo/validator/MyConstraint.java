package zz.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Time 2019/4/2
 * @Author zlian
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstrainValidator.class)
public @interface MyConstraint {
    //自定义校验必须有这三个属性
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
