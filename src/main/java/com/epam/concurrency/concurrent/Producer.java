package com.epam.concurrency.concurrent;

import com.epam.concurrency.generator.RequestGenerator;
import com.epam.concurrency.pojo.HotelBookingRequest;
import com.epam.concurrency.queue.CustomQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Producer implements Runnable {
    private static final Logger logger = LogManager.getLogger(Producer.class.getName());
    private CustomQueue queue;

    public Producer(CustomQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        HotelBookingRequest justProducedRequest = new HotelBookingRequest(
                RequestGenerator.getRandomNumberInRange(0, 999),
                RequestGenerator.getRandomLocalDate(),
                RequestGenerator.getRandomString(10));
        queue.put(justProducedRequest);
        logger.info("--------- Producer #" + Thread.currentThread().getName() +
                ": Produced resource " + justProducedRequest);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error(e);
            Thread.currentThread().interrupt();
        }
    }
}
