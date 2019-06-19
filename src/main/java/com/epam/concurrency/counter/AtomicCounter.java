package com.epam.concurrency.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private static final int MAX_NUMBER_OF_REQUESTS = 15;
    private static AtomicInteger count = new AtomicInteger();

    public static void inc() {
        boolean updated = false;
        while(!updated){
            int prevCount = count.get();
            updated = count.compareAndSet(prevCount, prevCount + 1);
        }
    }

    public static int count() {
        return count.get();
    }

    public static boolean isMoreRequests() {
        return count.get() < MAX_NUMBER_OF_REQUESTS;
    }
}
