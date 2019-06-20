package com.epam.concurrency.concurrent;

import com.epam.concurrency.generator.RequestGenerator;
import com.epam.concurrency.model.HotelBookingRequest;
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
        HotelBookingRequest justProducedRequest = RequestGenerator.generate();
        try {
            queue.put(justProducedRequest);
            logger.info("--------- Producer #" + Thread.currentThread().getName() +
                    ": Produced resource " + justProducedRequest);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error(e);
            Thread.currentThread().interrupt();
        }
    }

}
