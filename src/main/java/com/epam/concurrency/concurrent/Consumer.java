package com.epam.concurrency.concurrent;

import com.epam.concurrency.counter.AtomicCounter;
import com.epam.concurrency.model.HotelBookingRequest;
import com.epam.concurrency.queue.CustomQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Consumer implements Runnable {
    private static final Logger logger = LogManager.getLogger(Consumer.class.getName());
    private CustomQueue queue;

    public Consumer(CustomQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (AtomicCounter.count() < 15) {
                HotelBookingRequest request = queue.take();
                AtomicCounter.inc();
                logger.info("//////////////////// Consumer #" + Thread.currentThread().getName() +
                        ": Consumed resource " + request);
                Thread.sleep(2500);
            }
        } catch (InterruptedException e) {
            logger.error(e);
            Thread.currentThread().interrupt();
        }
    }
}
