package com.epam.concurrency.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private static int maxNumberOfRequests;
    private static AtomicInteger count = new AtomicInteger();

    public static void setMaxNumberOfRequests(int maxNumberOfRequests) {
        AtomicCounter.maxNumberOfRequests = maxNumberOfRequests;
    }

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
        return count.get() < maxNumberOfRequests;
    }
}
