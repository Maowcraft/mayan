package xyz.maow.mayan.test;

import xyz.maow.mayan.memory.Segment;
import xyz.maow.mayan.memory.scope.Scope;

public class Test {
    public static void main(String[] args) {
        try (Scope scope = Scope.confined()) {
            final Segment<Integer> seg = scope.ofInt(4);
        }
    }
}