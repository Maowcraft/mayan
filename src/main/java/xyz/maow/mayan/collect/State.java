package xyz.maow.mayan.collect;

import java.util.ArrayDeque;
import java.util.Deque;

public final class State<T> {
    private T t;
    private final Deque<T> deque;

    public State() {
        this.deque = new ArrayDeque<>();
    }

    public void set(T t) {
        deque.addFirst(this.t);
        this.t = t;
    }

    public T get() {
        return t;
    }

    public void revert() {
        this.t = deque.removeFirst();
    }

    public Deque<T> toDeque() {
        return deque;
    }
}