package xyz.maow.mayan.memory;

import xyz.maow.mayan.annotation.Unsafe;

@Unsafe
public class BooleanMemory extends AbstractMemory<Boolean> {
    public BooleanMemory(int size) {
        super(size, Byte.BYTES);
    }

    @Override
    public Boolean get(long index) {
        super.get(index);
        return unsafe.getByte(offset(index)) == 1;
    }

    @Override
    public void set(long index, Boolean value) {
        super.set(index, value);
        unsafe.putByte(offset(index), (byte) (value ? 1 : 0));
    }
}