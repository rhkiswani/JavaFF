package io.github.rhkiswani.javaff.validation;

import io.github.rhkiswani.javaff.validation.exceptions.*;
import io.github.rhkiswani.javaff.beans.EmployeeX;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

            }

            @Override
            public void validateList(List<EmployeeX> t) throws ValidationException {

            }

            @Override
            public void validateField(Field field, EmployeeX o) throws ValidationException {

            }

            @Override
            public void validateMethod(Method method, EmployeeX o) throws ValidationException {

            }
        });

        Validator<EmployeeX> xValidator = ValidatorFactory.getValidator(EmployeeX.class);
        xValidator.validate(null);
        xValidator.validateList(null);
        xValidator.validateField(null, null);
        xValidator.validateMethod(null, null);
        assertThat(xValidator).isNotNull();
    }

}
