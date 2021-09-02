package xyz.maow.mayan.function;

import java.util.function.Supplier;

@FunctionalInterface
public interface CheckedSupplier<T> extends Supplier<T> {
    @Override
    default T get() {
        try {
            return getChecked();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    T getChecked() throws Exception;
}