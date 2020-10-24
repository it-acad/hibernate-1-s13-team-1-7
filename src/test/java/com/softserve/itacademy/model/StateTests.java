package com.softserve.itacademy.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StateTests {
    @Test
    void createValidState() {
        State validState = new State();
        validState.setName("F");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(validState);

        assertEquals(0, violations.size());
    }

    @Test
    void constraintViolationOnEmptyStateName() {
        State emptyState = new State();
        emptyState.setName("");
        emptyState.setId(22);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(emptyState);
        assertEquals(1, violations.size());
    }
}
