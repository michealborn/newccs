package zz.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import zz.demo.service.HelloService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Time 2019/4/2
 * @Author zlian
 */
public class MyConstrainValidator implements ConstraintValidator<MyConstraint,Object> {

    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("init MyConstrainValidator");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        helloService.greeting("michealborn");
        System.out.println(o);
        return false;
    }
}
