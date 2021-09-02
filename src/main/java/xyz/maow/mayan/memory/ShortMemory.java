package xyz.maow.mayan.memory;

public class ShortMemory extends AbstractMemory<Short> {
    public ShortMemory(int size) {
        super(size, Short.BYTES);
    }

    @Override
    public Short get(long index) {
        super.get(index);
        return unsafe.getShort(offset(index));
    }

    @Override
    public void set(long index, Short value) {
        super.set(index, value);
        unsafe.putShort(offset(index), value);
    }
}