package com.epam.concurrency.concurrent;

import com.epam.concurrency.pojo.HotelBookingRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private int id;
    private static final Logger logger = LogManager.getLogger(Consumer.class.getName());
    private BlockingQueue<HotelBookingRequest> queue;

    public Consumer(BlockingQueue<HotelBookingRequest> queue, int id) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                HotelBookingRequest request = queue.take();
                logger.info("//////////////////// Consumer #" + id +
                        ": Consumed resource " + request +
                        " - Queue size now = "  + queue.size());
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
        logger.error(e);
    }
    }
}
