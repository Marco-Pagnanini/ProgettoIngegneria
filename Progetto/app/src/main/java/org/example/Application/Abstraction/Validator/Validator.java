package org.example.Application.Abstraction.Validator;

@FunctionalInterface
public interface Validator<T> {
    boolean validate(T entity);
}
