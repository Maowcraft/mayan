package xyz.maow.mayan.function;

import java.util.Objects;

public abstract class Either<L, R> {
    public static <L, R> Left<L, R> left(L left) {
        return new Left<>(left);
    }

    public static <L, R> Right<L, R> right(R right) {
        return new Right<>(right);
    }

    public abstract L getLeft();

    public abstract R getRight();

    public abstract Side getSide();

    public boolean isLeft() {
        return getSide() == Side.LEFT;
    }

    public boolean isRight() {
        return getSide() == Side.RIGHT;
    }

    public Either<L, R> onLeft(CheckedConsumer<L> consumer) {
        if (isLeft()) consumer.accept(getLeft());
        return this;
    }

    public Either<L, R> onRight(CheckedConsumer<R> consumer) {
        if (isRight()) consumer.accept(getRight());
        return this;
    }

    public enum Side {
        LEFT,
        RIGHT
    }

    public static class Left<L, R> extends Either<L, R> {
        private final L left;

        public Left(L left) {
            this.left = left;
        }

        @Override
        public L getLeft() {
            return left;
        }

        @Override
        public R getRight() {
            return null;
        }

        @Override
        public Side getSide() {
            return Side.LEFT;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Left<?, ?> left1 = (Left<?, ?>) o;
            return Objects.equals(left, left1.left);
        }

        @Override
        public int hashCode() {
            return Objects.hash(left);
        }
    }

    public static class Right<L, R> extends Either<L, R> {
        private final R right;

        public Right(R r) {
            this.right = r;
        }

        @Override
        public L getLeft() {
            return null;
        }

        @Override
        public R getRight() {
            return right;
        }

        @Override
        public Side getSide() {
            return Side.RIGHT;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Right<?, ?> right1 = (Right<?, ?>) o;
            return Objects.equals(right, right1.right);
        }

        @Override
        public int hashCode() {
            return Objects.hash(right);
        }
    }
}