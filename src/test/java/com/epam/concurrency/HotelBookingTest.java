package com.epam.concurrency;

import com.epam.concurrency.concurrent.Consumer;
import com.epam.concurrency.concurrent.Producer;
import com.epam.concurrency.counter.AtomicCounter;
import com.epam.concurrency.generator.RequestGenerator;
import com.epam.concurrency.model.HotelBookingRequest;
import com.epam.concurrency.queue.CustomQueue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HotelBookingTest {
    private CustomQueue queue;
    private ExecutorService producerExecutorService;
    private ExecutorService consumerExecutorService;

    @BeforeEach
    void setUp() {
        queue = new CustomQueue(5);
        producerExecutorService = Executors.newFixedThreadPool(3);
        consumerExecutorService = Executors.newFixedThreadPool(6);
    }

    @AfterEach
    void tearDown() {
        producerExecutorService.shutdown();
        consumerExecutorService.shutdown();
    }

    @Test
    void testProducerGenerationOfRequests() throws InterruptedException {
        IntStream.range(0, 15).forEach(i -> producerExecutorService.submit(new Producer(queue)));
        IntStream.range(0, 15).forEach(i -> consumerExecutorService.submit(new Consumer(queue)));
        Thread.sleep(10000);
        assertEquals(15, AtomicCounter.counter.get());
    }

    @Test
    void testCustomQueue() throws InterruptedException {
        HotelBookingRequest request = RequestGenerator.generate();
        queue.put(request);
        assertEquals(request, queue.take());
    }
}
