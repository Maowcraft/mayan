package xyz.maow.mayan.memory.scope;

import xyz.maow.mayan.memory.Segment;

import java.util.HashSet;
import java.util.Set;

final class ConfinedScope implements Scope {
    private final Thread owner;
    private final Set<Segment<?>> segments;
    private int count;
    private boolean alive;

    ConfinedScope(Thread owner) {
        this.owner = owner;
        this.segments = new HashSet<>();
        this.count = 0;
        this.alive = true;
    }

    @Override
    public void claim(Segment<?> segment) {
        validate();
        segments.add(segment);
    }

    @Override
    public boolean alive() {
        return alive;
    }

    @Override
    public void close() {
        validate();
        if (count == 0) {
            for (Segment<?> segment : segments)
                segment.close();
            alive = true;
        } else {
            throw new UnsupportedOperationException("Confined scope is acquired by " + count + " locks");
        }
    }

    @Override
    public Handle acquire() {
        validate();
        count++;
        return new ConfinedHandle();
    }

    private void validate() {
        if (owner != Thread.currentThread())
            throw new IllegalStateException("Attempted to access confined scope outside of the thread it belongs to");
        if (!alive)
            throw new IllegalStateException("Attempted to access confined scope after it has already closed");
    }

    public Thread owner() {
        return owner;
    }

    final class ConfinedHandle implements Scope.Handle {
        private boolean released = false;

        @Override
        public Scope scope() {
            return ConfinedScope.this;
        }

        @Override
        public void release() {
            validate();
            if (!released) {
                released = true;
                count--;
            }
        }
    }
}