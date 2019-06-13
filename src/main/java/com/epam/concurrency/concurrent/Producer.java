package com.epam.concurrency.concurrent;

import com.epam.concurrency.generator.RequestGenerator;
import com.epam.concurrency.pojo.HotelBookingRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private int id;
    private static final Logger logger = LogManager.getLogger(Producer.class.getName());
    private BlockingQueue<HotelBookingRequest> queue;
    private int maxNumberOfGeneratedRequests = 15;

    public Producer(BlockingQueue<HotelBookingRequest> queue, int id) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (maxNumberOfGeneratedRequests > 0) {
                HotelBookingRequest justProducedRequest = new HotelBookingRequest(
                        RequestGenerator.getRandomNumberInRange(111111111, 999999999),
                        RequestGenerator.getRandomLocalDate(),
                        RequestGenerator.getRandomString(10));
                queue.put(justProducedRequest);
                logger.info("//////////////////// Producer #" + id +
                        ": Produced resource " + justProducedRequest +
                        " - Queue size now = " + queue.size());
                maxNumberOfGeneratedRequests--;
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }
}
