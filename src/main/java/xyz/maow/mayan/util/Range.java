package xyz.maow.mayan.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

public final class Range implements Iterable<Integer>, Comparable<Range> {
    private final int min;
    private final int max;

    public Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static Range max(int max) {
        return new Range(0, max);
    }

    public boolean in(int i) {
        return i > min && i < max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int current = min;

            @Override
            public boolean hasNext() {
                return current < max;
            }

            @Override
            public Integer next() {
                return current++;
            }
        };
    }

    @Override
    public int compareTo(Range o) {
        return Objects.compare(this, o, Comparator.naturalOrder());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Range integers = (Range) o;
        return min == integers.min && max == integers.max;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }
}