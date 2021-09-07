package xyz.maow.mayan.memory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

@SuppressWarnings("sunapi")
final class UnsafeAccess {
    static final Unsafe unsafe;

    private UnsafeAccess() {
        throw new UnsupportedOperationException();
    }

    static {
        Unsafe unsafe0 = null;
        try {
            final Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe0 = (Unsafe) f.get(null);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        unsafe = unsafe0;
    }
}