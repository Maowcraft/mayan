package xyz.maow.mayan.memory;

import static xyz.maow.mayan.memory.UnsafeAccess.unsafe;

@SuppressWarnings("sunapi")
public abstract class AbstractMemory<T> implements Memory<T> {
    private final long address;
    private int size;
    private final long offset;

    protected AbstractMemory(int size, long offset) {
        this.address = unsafe.allocateMemory(size * offset);
        this.size = size;
        this.offset = offset;
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