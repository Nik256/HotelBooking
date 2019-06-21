package com.epam.concurrency;

import com.epam.concurrency.concurrent.Consumer;
import com.epam.concurrency.concurrent.Producer;
import com.epam.concurrency.counter.AtomicCounter;
import com.epam.concurrency.generator.RequestGenerator;
import com.epam.concurrency.model.HotelBookingRequest;
import com.epam.concurrency.queue.CustomQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HotelBookingTest {
    private CustomQueue queue;
    private ExecutorService producerExecutorService;
    private ExecutorService consumerExecutorService;

    @BeforeEach
    void setUp() {
        queue = new CustomQueue(5);
    }

    @Test
    void testProducerGenerationOfRequests() throws InterruptedException {
        AtomicCounter.setMaxNumberOfRequests(15);
        producerExecutorService = Executors.newFixedThreadPool(3);
        consumerExecutorService = Executors.newFixedThreadPool(6);
        IntStream.range(0, 15).forEach(i -> producerExecutorService.submit(new Producer(queue)));
        IntStream.range(0, 6).forEach(i -> consumerExecutorService.submit(new Consumer(queue)));
        producerExecutorService.shutdown();
        consumerExecutorService.shutdown();
        producerExecutorService.awaitTermination(30, TimeUnit.SECONDS);
        consumerExecutorService.awaitTermination(30, TimeUnit.SECONDS);
        assertEquals(15, AtomicCounter.count());
    }

    @Test
    void testCustomQueue() throws InterruptedException {
        HotelBookingRequest request = RequestGenerator.generate();
        queue.put(request);
        queue.put(RequestGenerator.generate());
        assertEquals(request, queue.take());
    }
}
