package xyz.maow.mayan.memory;

import sun.misc.Unsafe;
import xyz.maow.mayan.function.Lazy;

import java.lang.reflect.Field;

@SuppressWarnings("sunapi")
final class UnsafeAccess {
    private static final Lazy<Unsafe> unsafe = Lazy.of(() -> {
        try {
            final Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (ReflectiveOperationException ex) {
            ex.printStackTrace();
        }
        return null;
    });

    private UnsafeAccess() {
        throw new UnsupportedOperationException();
    }

    static Unsafe unsafe() {
        return unsafe.evaluate();
    }
}