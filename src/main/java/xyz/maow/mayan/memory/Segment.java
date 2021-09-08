package xyz.maow.mayan.memory;

import xyz.maow.mayan.memory.scope.Scope;

import java.io.Closeable;
import java.util.List;
import java.util.Set;

public interface Segment<T> extends Closeable, Iterable<T> {
    void set(long index, T value);

    void set(long index, T[] values);

    T get(long index);

    void fill(T value);

    Segment<T> slice(long start, long size);

    default Segment<T> slice(long start) {
        return slice(start, size() - start);
    }

    Segment<T> copy();

    Segment<T> copySlice(long start, long size);

    void copyTo(Segment<T> segment);

    long reallocate(long size);

    @Override void close();

    // === Properties ===

    Scope scope();

    long address();

    long size();

    long offset();

    // === Conversions ===

    List<T> toList();

    Set<T> toSet();
}