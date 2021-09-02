package xyz.maow.mayan.util;

import java.util.Iterator;

public final class Iterables {
    private Iterables() {
        throw new UnsupportedOperationException();
    }

    public static Iterable<Character> from(String s) {
        return () -> new Iterator<Character>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < s.length();
            }

            @Override
            public Character next() {
                final char c = s.charAt(index);
                index++;
                return c;
            }
        };
    }
}