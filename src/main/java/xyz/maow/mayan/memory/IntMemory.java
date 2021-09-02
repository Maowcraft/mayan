package xyz.maow.mayan.memory;

import xyz.maow.mayan.annotation.Unsafe;

@Unsafe
public class IntMemory extends AbstractMemory<Integer> {
    public IntMemory(int size) {
        super(size, Integer.BYTES);
    }

    @Override
    public Integer get(long index) {
        super.get(index);
        return unsafe.getInt(offset(index));
    }

    @Override
    public void set(long index, Integer value) {
        super.set(index, value);
        unsafe.putInt(offset(index), value);
    }
}