package xyz.maow.mayan.memory;

import xyz.maow.mayan.annotation.Unsafe;

@Unsafe
public class DoubleMemory extends AbstractMemory<Double> {
    public DoubleMemory(int size) {
        super(size, Double.BYTES);
    }

    @Override
    public Double get(long index) {
        super.get(index);
        return unsafe.getDouble(offset(index));
    }

    @Override
    public void set(long index, Double value) {
        super.set(index, value);
        unsafe.putDouble(offset(index), value);
    }
}