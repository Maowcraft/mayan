package xyz.maow.mayan.collect;

import java.util.function.BiConsumer;

public interface IndexedIterable<T> extends Iterable<T> {
    default void forEachIndexed(BiConsumer<T, Integer> consumer) {
        int index = 0;
        for (T t : this) {
            consumer.accept(t, index);
            index++;
        }
    }
}