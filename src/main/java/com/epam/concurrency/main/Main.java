package com.epam.concurrency.main;

import com.epam.concurrency.concurrent.Consumer;
import com.epam.concurrency.concurrent.Producer;
import com.epam.concurrency.queue.CustomQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Main {
    private static final int QUEUE_CAPACITY = 5;
    private static final int NUMBER_OF_REQUESTS = 15;
    private static final int NUMBER_OF_PRODUCERS = 3;
    private static final int NUMBER_OF_CONSUMERS = 6;

    public static void main(String[] args) {
        CustomQueue queue = new CustomQueue(QUEUE_CAPACITY);

        ExecutorService producerExecutorService = Executors.newFixedThreadPool(NUMBER_OF_PRODUCERS);
        ExecutorService consumerExecutorService = Executors.newFixedThreadPool(NUMBER_OF_CONSUMERS);

        IntStream.range(0, NUMBER_OF_REQUESTS).forEach(i -> producerExecutorService.submit(new Producer(queue)));
        IntStream.range(0, NUMBER_OF_CONSUMERS).forEach(i -> consumerExecutorService.submit(new Consumer(queue)));

        producerExecutorService.shutdown();
        consumerExecutorService.shutdown();
    }
}
