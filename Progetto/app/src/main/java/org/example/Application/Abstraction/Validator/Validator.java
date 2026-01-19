package org.example.Application.Abstraction.Validator;

public interface Validator<T> {
    boolean validate(T entity);
}
