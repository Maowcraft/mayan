package xyz.maow.mayan.test;

import xyz.maow.mayan.memory.Memory;

public class Test {
    public static void main(String[] args) {
        try (Memory<Integer> memory = Memory.ofInt(1)) {
            memory.set(0, 69);
            System.out.println(memory.get(0));
        }

//        final Either<String, Exception> either = Either.right(new Exception());
//        either
//                .onLeft(System.out::println)
//                .onRight(ex -> {
//                    throw ex;
//                });
    }
}