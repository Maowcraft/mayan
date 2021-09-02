package xyz.maow.mayan.memory;

import xyz.maow.mayan.annotation.Unsafe;

import java.io.Closeable;

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
        return new BooleanMemory(size);
    }

    static Memory<Byte> ofByte(int size) {
        return new ByteMemory(size);
    }

    static Memory<Character> ofChar(int size) {
        return new CharMemory(size);
    }

    static Memory<Short> ofShort(int size) {
        return new ShortMemory(size);
    }

    static Memory<Integer> ofInt(int size) {
        return new IntMemory(size);
    }

    static Memory<Long> ofLong(int size) {
        return new LongMemory(size);
    }

    static Memory<Float> ofFloat(int size) {
        return new FloatMemory(size);
    }

    static Memory<Double> ofDouble(int size) {
        return new DoubleMemory(size);
    }
}