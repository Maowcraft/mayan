package xyz.maow.mayan.memory;

import xyz.maow.mayan.memory.scope.Scope;

import java.util.function.BiConsumer;
import java.util.function.Function;

import static xyz.maow.mayan.internal.util.UnsafeAccess.unsafe;

public final class Memory {
    private final Scope scope;

    public Memory(Scope scope) {
        this.scope = scope;
    }

    public Memory() {
        this(null);
    }

    public Segment<Boolean> ofBool(long size) {
        return new BoolSegmentImpl(scope, size);
    }

    public Segment<Byte> ofByte(long size) {
        return of(size, Byte.BYTES, unsafe::putByte, unsafe::getByte);
    }

    public Segment<Character> ofChar(long size) {
        return of(size, Character.BYTES, unsafe::putChar, unsafe::getChar);
    }

    public Segment<Short> ofShort(long size) {
        return of(size, Short.BYTES, unsafe::putShort, unsafe::getShort);
    }

    public Segment<Integer> ofInt(long size) {
        return of(size, Integer.BYTES, unsafe::putInt, unsafe::getInt);
    }

    public Segment<Long> ofLong(long size) {
        return of(size, Long.BYTES, unsafe::putLong, unsafe::getLong);
    }

    public Segment<Float> ofFloat(long size) {
        return of(size, Float.BYTES, unsafe::putFloat, unsafe::getFloat);
    }

    public Segment<Double> ofDouble(long size) {
        return of(size, Double.BYTES, unsafe::putDouble, unsafe::getDouble);
    }

    public Segment<Boolean> fromBool(long address, long size) {
        return new BoolSegmentImpl(scope, address, size);
    }

    public Segment<Byte> fromByte(long address, long size) {
        return from(address, size, Byte.BYTES, unsafe::putByte, unsafe::getByte);
    }

    public Segment<Character> fromChar(long address, long size) {
        return from(address, size, Character.BYTES, unsafe::putChar, unsafe::getChar);
    }

    public Segment<Short> fromShort(long address, long size) {
        return from(address, size, Short.BYTES, unsafe::putShort, unsafe::getShort);
    }

    public Segment<Integer> fromInt(long address, long size) {
        return from(address, size, Integer.BYTES, unsafe::putInt, unsafe::getInt);
    }

    public Segment<Long> fromLong(long address, long size) {
        return from(address, size, Long.BYTES, unsafe::putLong, unsafe::getLong);
    }

    public Segment<Float> fromFloat(long address, long size) {
        return from(address, size, Float.BYTES, unsafe::putFloat, unsafe::getFloat);
    }

    public Segment<Double> fromDouble(long address, long size) {
        return from(address, size, Double.BYTES, unsafe::putDouble, unsafe::getDouble);
    }

    private <T> Segment<T> of(long size, long offset, BiConsumer<Long, T> setter, Function<Long, T> getter) {
        return new SegmentImpl<>(null, size, offset, setter, getter);
    }

    private <T> Segment<T> from(long address, long size, long offset, BiConsumer<Long, T> setter, Function<Long, T> getter) {
        return new SegmentImpl<>(scope, address, size, offset, setter, getter);
    }
}