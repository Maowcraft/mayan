package xyz.maow.mayan.memory.scope;

import xyz.maow.mayan.memory.Segment;

final class GlobalScope implements Scope, Scope.Handle {
    static final Scope INSTANCE = new GlobalScope();

    private GlobalScope() {}

    @Override
    public void claim(Segment<?> segment) {}

    @Override
    public boolean alive() {
        return true;
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Cannot close global scope");
    }

    @Override
    public Handle acquire() {
        return this;
    }

    @Override
    public void release() {}

    @Override
    public Scope scope() {
        return this;
    }
}