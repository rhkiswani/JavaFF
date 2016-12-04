package io.github.rhkiswani.javaff.validation;

import io.github.rhkiswani.javaff.exceptions.SmartException;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import io.github.rhkiswani.javaff.reflection.ReflectionUtil;
import io.github.rhkiswani.javaff.validation.exceptions.*;
import io.github.rhkiswani.javaff.beans.EmployeeX;
import org.junit.Test;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorFactoryTest {

    @Test
    // for coverage only , the actual implementation will be in JavaValidation
    public void testValidatorFactory() {
        assertThat(ValidatorFactory.instance() == ValidatorFactory.instance()).isEqualTo(true);
        ValidatorFactory.instance().add(EmployeeX.class, new Validator<EmployeeX>() {
            @Override
            public void validate(EmployeeX o) throws ValidationException {
                List<Field> fields = ReflectionUtil.getFields(EmployeeX.class);
                for (Field field : fields) {
                    validateField(field, o);
                }
            }

            @Override
            public void validateList(List<EmployeeX> t) throws ValidationException {
                for (EmployeeX employeeX : t) {
                    validate(employeeX);
                }
            }

            @Override
            public void validateField(Field field, EmployeeX o) throws ValidationException {
                if (field.isAnnotationPresent(NotNull.class) && ReflectionUtil.getFieldValue(o, field.getName()) == null ){
                    throw new IllegalParamException(SmartException.NULL_VAL, field.getName());
                }
            }

            @Override
            public void validateMethod(Method method, EmployeeX o) throws ValidationException {
                throw new ValidationException(SmartException.NO_IMPLEMENTATION_FOUND);
            }
        });

        Validator<EmployeeX> xValidator = ValidatorFactory.getValidator(EmployeeX.class);
        xValidator.validateList(Arrays.asList(new EmployeeX()));
        try {
            xValidator.validateMethod(null, null);
        } catch (Exception e){

        }
        assertThat(xValidator).isNotNull();
    }

}
