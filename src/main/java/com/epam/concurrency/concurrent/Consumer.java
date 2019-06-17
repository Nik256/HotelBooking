package com.epam.concurrency.concurrent;

import com.epam.concurrency.pojo.HotelBookingRequest;
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
        HotelBookingRequest request = queue.take();
        logger.info("//////////////////// Consumer #" + Thread.currentThread().getName() +
                ": Consumed resource " + request);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error(e);
            Thread.currentThread().interrupt();
        }
    }
}
