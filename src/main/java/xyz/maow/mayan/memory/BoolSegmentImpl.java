package xyz.maow.mayan.memory;

import xyz.maow.mayan.memory.scope.Scope;

import static xyz.maow.mayan.internal.util.UnsafeAccess.unsafe;

final class BoolSegmentImpl extends SegmentImpl<Boolean> {
    BoolSegmentImpl(Scope scope, long size) {
        super(scope, size, Byte.BYTES, null, null);
    }

    BoolSegmentImpl(Scope scope, long address, long size) {
        super(scope, address, size, Byte.BYTES, null, null);
    }

    @Override
    public Boolean get(long index) {
        checkIndex(index);
        return unsafe.getByte(address() + index) == 1;
    }

    @Override
    public void set(long index, Boolean value) {
        checkIndex(index);
        unsafe.putByte(address() + index, (byte) (value ? 1 : 0));
    }
}