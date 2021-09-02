package xyz.maow.mayan.memory;

import xyz.maow.mayan.annotation.Unsafe;

@Unsafe
public class ByteMemory extends AbstractMemory<Byte> {
    public ByteMemory(int size) {
        super(size, Byte.BYTES);
    }

    @Override
    public Byte get(long index) {
        super.get(index);
        return unsafe.getByte(offset(index));
    }

    @Override
    public void set(long index, Byte value) {
        super.set(index, value);
        unsafe.putByte(offset(index), value);
    }
}