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
public class TaskTests {
    @Test
    void constraintViolationOnEmptyTaskName() {
        Task emptyTask = new Task();

        emptyTask.setName("");
        emptyTask.setId(0);
        State state = new State();
        emptyTask.setState(state);
        emptyTask.setPriority( Priority.HIGH );
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(emptyTask);
        assertEquals(1, violations.size());
    }

    @Test
    void createValidTask() {
        Task validTask = new Task();
        validTask.setName("F");
        ToDo toDo = new ToDo();
        validTask.setToDo(toDo);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(validTask);

        assertEquals(0, violations.size());
    }
}
