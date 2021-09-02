package xyz.maow.mayan.memory;

import xyz.maow.mayan.annotation.Unsafe;

@Unsafe
public class FloatMemory extends AbstractMemory<Float> {
    public FloatMemory(int size) {
        super(size, Float.BYTES);
    }

    @Override
    public Float get(long index) {
        super.get(index);
        return unsafe.getFloat(offset(index));
    }

    @Override
    public void set(long index, Float value) {
        super.set(index, value);
        unsafe.putFloat(offset(index), value);
    }
}