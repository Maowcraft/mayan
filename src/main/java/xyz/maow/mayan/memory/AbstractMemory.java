package xyz.maow.mayan.memory;

import sun.misc.Unsafe;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

@xyz.maow.mayan.annotation.Unsafe
@SuppressWarnings("sunapi")
public abstract class AbstractMemory<T> extends AbstractCollection<T> implements Memory<T> {
    private final long address;
    private int size;
    private final long offset;

    protected final Unsafe unsafe = UnsafeAccess.unsafe();

    protected AbstractMemory(int size, long offset) {
        this.address = unsafe.allocateMemory(size * offset);
        this.size = size;
        this.offset = offset;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                final T t = get(index);
                index++;
                return t;
            }
        };
    }

    @Override
    public Object[] toArray() {
        final Object[] arr = new Object[size];
        forEachIndexed((t, i) -> arr[i] = t);
        return arr;
    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(long index, T value) {
        check(index);
    }

    @Override
    public T get(long index) {
        check(index);
        return null;
    }

    @Override
    public void reallocate(int size) {
        this.size = size;
        unsafe.reallocateMemory(address, size * offset);
    }

    @Override
    public long address() {
        return address;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long offset() {
        return offset;
    }

    @Override
    public void close() {
        unsafe.freeMemory(address);
    }

    protected long offset(long index) {
        return address + index * offset;
    }

    private void check(long index) {
        if (index > size() - 1) throw new IndexOutOfBoundsException("Index: " + index + " | Size: " + size());
        if (index < -1)         throw new IndexOutOfBoundsException("Index: " + index);
    }
}