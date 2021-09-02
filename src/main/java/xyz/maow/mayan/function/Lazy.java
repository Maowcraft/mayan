package xyz.maow.mayan.function;

import java.util.function.Supplier;

@FunctionalInterface
public interface Lazy<T> extends Supplier<T> {
    static <T> Lazy<T> of(CheckedSupplier<T> supplier) {
        return new Lazy<T>() {
            private T value = null;

            @Override
            public T get() {
                if (value == null)
                    value = supplier.get();
                return value;
            }
        };
    }
}