package xyz.maow.mayan.memory;

import xyz.maow.mayan.annotation.Unsafe;

import java.io.Closeable;

import static xyz.maow.mayan.memory.UnsafeAccess.unsafe;

@Unsafe
public interface Memory<T> extends Closeable {
    void set(long index, T value);

    T get(long index);

    void reallocate(int size);

    long address();

    int size();

    long offset();

    // =========
    // Static Factory Methods
    // =========

    static Memory<Boolean> ofBoolean(int size) {
        return new BoolMemory(size);
    }

    static Memory<Byte> ofByte(int size) {
        return MemFactory.from(unsafe()::getByte, unsafe()::putByte, size, Byte.BYTES);
    }

    static Memory<Character> ofChar(int size) {
        return MemFactory.from(unsafe()::getChar, unsafe()::putChar, size, Character.BYTES);
    }

    static Memory<Short> ofShort(int size) {
        return MemFactory.from(unsafe()::getShort, unsafe()::putShort, size, Short.BYTES);
    }

    static Memory<Integer> ofInt(int size) {
        return MemFactory.from(unsafe()::getInt, unsafe()::putInt, size, Integer.BYTES);
    }

    static Memory<Long> ofLong(int size) {
        return MemFactory.from(unsafe()::getLong, unsafe()::putLong, size, Long.BYTES);
    }

    static Memory<Float> ofFloat(int size) {
        return MemFactory.from(unsafe()::getFloat, unsafe()::putFloat, size, Float.BYTES);
    }

    static Memory<Double> ofDouble(int size) {
        return MemFactory.from(unsafe()::getDouble, unsafe()::putDouble, size, Double.BYTES);
    }
}