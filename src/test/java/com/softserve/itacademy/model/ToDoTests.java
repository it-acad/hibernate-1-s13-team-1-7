package com.softserve.itacademy.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ToDoTests {

    @ParameterizedTest
    @MethodSource("provideInvalidEmailUser")
    void constraintViolationInvalidTitle(String input) {
        ToDo toDo = new ToDo();
        toDo.setTitle(input);
        User user = new User();
        user.setId(11);
        toDo.setOwner(user);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.usingContext().getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(toDo);

        assertEquals(0, violations.size());
    }
        private static Stream<Arguments> provideInvalidEmailUser() {
            return Stream.of(
                    Arguments.of("invalid"),
                    Arguments.of("title@"),
                    Arguments.of("valid")
            );
        }

    @Test
    void createValidToDo() {
        ToDo validToDo = new ToDo();
        validToDo.setTitle("F");
        validToDo.setId(12);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(validToDo);

        assertEquals(0, violations.size());
    }
}

