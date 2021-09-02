package xyz.maow.mayan.function;

import java.util.function.Function;

@FunctionalInterface
public interface CheckedFunction<T, R> extends Function<T, R> {
    @Override
    default R apply(T t) {
        try {
            return applyChecked(t);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    R applyChecked(T t) throws Exception;
}