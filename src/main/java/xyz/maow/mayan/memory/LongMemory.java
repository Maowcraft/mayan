package xyz.maow.mayan.memory;

import xyz.maow.mayan.annotation.Unsafe;

@Unsafe
public class LongMemory extends AbstractMemory<Long> {
    public LongMemory(int size) {
        super(size, Long.BYTES);
    }

    @Override
    public Long get(long index) {
        super.get(index);
        return unsafe.getLong(offset(index));
    }

    @Override
    public void set(long index, Long value) {
        super.set(index, value);
        unsafe.putLong(offset(index), value);
    }
}