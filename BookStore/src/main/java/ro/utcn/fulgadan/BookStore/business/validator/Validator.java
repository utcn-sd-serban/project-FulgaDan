package ro.utcn.fulgadan.BookStore.business.validator;

public interface Validator<T> {

    boolean validate(T t);
}
