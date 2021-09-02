package xyz.maow.mayan.function;

import java.util.function.Supplier;

public interface Lazy<T> {
    T evaluate();

    static <T> Lazy<T> of(Supplier<T> supplier) {
        return new Lazy<T>() {
            private T value = null;

            @Override
            public T evaluate() {
                if (value == null)
                    value = supplier.get();
                return value;
            }
        };
    }
}