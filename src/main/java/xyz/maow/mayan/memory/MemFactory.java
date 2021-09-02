package xyz.maow.mayan.memory;

import java.util.function.BiConsumer;
import java.util.function.Function;

final class MemFactory {
    private MemFactory() {
        throw new UnsupportedOperationException();
    }

    static <T> Memory<T> from(Function<Long, T> getter,
                              BiConsumer<Long, T> setter,
                              int size,
                              long offset) {
        return new AbstractMemory<T>(size, offset) {
            @Override
            public void set(long index, T value) {
                super.set(index, value);
                setter.accept(offset(index), value);
            }

            @Override
            public T get(long index) {
                super.get(index);
                return getter.apply(offset(index));
            }
        };
    }
}