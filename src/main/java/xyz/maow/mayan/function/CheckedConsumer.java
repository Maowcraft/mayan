package xyz.maow.mayan.function;

import java.util.function.Consumer;

@FunctionalInterface
public interface CheckedConsumer<T> extends Consumer<T> {
    @Override
    default void accept(T t) {
        try {
            acceptChecked(t);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    void acceptChecked(T t) throws Exception;
}