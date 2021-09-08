package xyz.maow.mayan.memory;

import xyz.maow.mayan.memory.scope.Scope;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static xyz.maow.mayan.internal.util.UnsafeAccess.unsafe;

class SegmentImpl<T> implements Segment<T> {
    private final Scope scope;
    private final long address;
    private final long size;
    private final long offset;

    private final BiConsumer<Long, T> setter;
    private final Function<Long, T> getter;

    SegmentImpl(Scope scope, long size, long offset,
                BiConsumer<Long, T> setter,
                Function<Long, T> getter) {
        this(scope, unsafe.allocateMemory(size * offset), size, offset, setter, getter);
        if (scope != null) scope.claim(this);
    }

    SegmentImpl(Scope scope, long address, long size, long offset,
                BiConsumer<Long, T> setter,
                Function<Long, T> getter) {
        this.scope = scope;
        this.address = address;
        this.size = size;
        this.offset = offset;
        this.setter = setter;
        this.getter = getter;
    }

    @Override
    public void set(long index, T value) {
        checkIndex(index);
        setter.accept(address + index * offset, value);
    }

    @Override
    public void set(long index, T[] values) {
        if (values.length > size) throw new IndexOutOfBoundsException("Array is too large");
        for (int i = 0; i < values.length; i++)
            set(index + i, values[i]);
    }

    @Override
    public T get(long index) {
        checkIndex(index);
        return getter.apply(address + index * offset);
    }

    @Override
    public void fill(T value) {
        for (int i = 0; i < size; i++)
            set(i, value);
    }

    @Override
    public Segment<T> slice(long start, long size) {
        checkIndex(start);
        checkSize(size);
        return new SegmentImpl<>(scope, address + start, size, setter, getter);
    }

    @Override
    public Segment<T> copy() {
        final Segment<T> seg = new SegmentImpl<>(scope, size, offset, setter, getter);
        unsafe.copyMemory(address, seg.address(), size);
        return seg;
    }

    @Override
    public Segment<T> copySlice(long start, long size) {
        checkIndex(start);
        checkSize(size);
        final Segment<T> seg = new SegmentImpl<>(scope, size, offset, setter, getter);
        unsafe.copyMemory(address + start, seg.address(), size);
        return seg;
    }

    @Override
    public void copyTo(Segment<T> segment) {
        unsafe.copyMemory(address, segment.address(), size);
    }

    @Override
    public long reallocate(long size) {
        checkSize(size);
        return unsafe.reallocateMemory(address, size);
    }

    @Override
    public void close() {
        unsafe.freeMemory(address);
    }

    @Override
    public Scope scope() {
        return scope;
    }

    @Override
    public long address() {
        return address;
    }

    @Override
    public long size() {
        return size;
    }

    @Override
    public long offset() {
        return offset;
    }

    @Override
    public List<T> toList() {
        final List<T> list = new ArrayList<>();
        forEach(list::add);
        return list;
    }

    @Override
    public Set<T> toSet() {
        final Set<T> set = new HashSet<>();
        forEach(set::add);
        return set;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private long index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return get(index++);
            }
        };
    }

    protected void checkIndex(long index) {
        if (index > size - 1) throw new IndexOutOfBoundsException("Index: " + index + " | Size: " + size);
        if (index < -1)       throw new IndexOutOfBoundsException("Index: " + index);
    }

    protected void checkSize(long size) {
        if (this.size > size - 1) throw new IndexOutOfBoundsException("Specified Size: " + size + " | Size: " + size);
        if (this.size < -1)       throw new IndexOutOfBoundsException("Specified Size: " + size);
    }
}