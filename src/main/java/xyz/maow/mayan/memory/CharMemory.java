package xyz.maow.mayan.memory;

import xyz.maow.mayan.annotation.Unsafe;

@Unsafe
public class CharMemory extends AbstractMemory<Character> {
    public CharMemory(int size) {
        super(size, Character.BYTES);
    }

    @Override
    public Character get(long index) {
        super.get(index);
        return unsafe.getChar(offset(index));
    }

    @Override
    public void set(long index, Character value) {
        super.set(index, value);
        unsafe.putChar(offset(index), value);
    }
}