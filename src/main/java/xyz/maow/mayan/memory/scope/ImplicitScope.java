package xyz.maow.mayan.memory.scope;

import xyz.maow.mayan.memory.Segment;

import java.util.HashSet;
import java.util.Set;

final class ImplicitScope implements Scope, Scope.Handle {
    private final Set<Segment<?>> segments;

    ImplicitScope() {
        this.segments = new HashSet<>();
    }

    @Override
    public void claim(Segment<?> segment) {
        segments.add(segment);
    }

    @Override
    public boolean alive() {
        return false;
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Implicit scope cannot be closed");
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

    @Override
    protected void finalize() {
        for (Segment<?> segment : segments)
            segment.close();
    }
}