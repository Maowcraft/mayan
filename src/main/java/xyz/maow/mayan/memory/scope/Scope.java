package xyz.maow.mayan.memory.scope;

import xyz.maow.mayan.memory.Memory;
import xyz.maow.mayan.memory.Segment;

import java.io.Closeable;

public interface Scope extends Closeable {
    void claim(Segment<?> segment);

    boolean alive();

    @Override void close();

    interface Handle {
        Scope scope();

        void release();
    }

    Handle acquire();

    // === Util Methods ===

    default Segment<Boolean> ofBool(long size) {
        return new Memory(this).ofBool(size);
    }

    default Segment<Byte> ofByte(long size) {
        return new Memory(this).ofByte(size);
    }

    default Segment<Character> ofChar(long size) {
        return new Memory(this).ofChar(size);
    }

    default Segment<Short> ofShort(long size) {
        return new Memory(this).ofShort(size);
    }

    default Segment<Integer> ofInt(long size) {
        return new Memory(this).ofInt(size);
    }

    default Segment<Long> ofLong(long size) {
        return new Memory(this).ofLong(size);
    }

    default Segment<Float> ofFloat(long size) {
        return new Memory(this).ofFloat(size);
    }

    default Segment<Double> ofDouble(long size) {
        return new Memory(this).ofDouble(size);
    }

    default Segment<Boolean> fromBool(long address, long size) {
        return new Memory(this).fromBool(address, size);
    }

    default Segment<Byte> fromByte(long address, long size) {
        return new Memory(this).fromByte(address, size);
    }

    default Segment<Character> fromChar(long address, long size) {
        return new Memory(this).fromChar(address, size);
    }

    default Segment<Short> fromShort(long address, long size) {
        return new Memory(this).fromShort(address, size);
    }

    default Segment<Integer> fromInt(long address, long size) {
        return new Memory(this).fromInt(address, size);
    }

    default Segment<Long> fromLong(long address, long size) {
        return new Memory(this).fromLong(address, size);
    }

    default Segment<Float> fromFloat(long address, long size) {
        return new Memory(this).fromFloat(address, size);
    }

    default Segment<Double> fromDouble(long address, long size) {
        return new Memory(this).fromDouble(address, size);
    }

    // === Static Factory Methods ===

    static Scope confined() {
        return new ConfinedScope(Thread.currentThread());
    }

    static Scope confined(Thread owner) {
        return new ConfinedScope(owner);
    }

    static Scope implicit() {
        return new ImplicitScope();
    }

    static Scope global() {
        return GlobalScope.INSTANCE;
    }
}