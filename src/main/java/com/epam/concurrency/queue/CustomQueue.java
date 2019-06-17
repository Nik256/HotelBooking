package com.epam.concurrency.queue;

import com.epam.concurrency.pojo.HotelBookingRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

public class CustomQueue {
    private static final Logger logger = LogManager.getLogger(CustomQueue.class.getName());
    private Queue<HotelBookingRequest> queue;
    private int capacity;

    public CustomQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void put(HotelBookingRequest request) {
        while (queue.size() == capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
               logger.error(e);
               Thread.currentThread().interrupt();
            }
        }
        if(queue.isEmpty()) {
            notifyAll();
        }
        queue.add(request);
    }

    public synchronized HotelBookingRequest take() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                logger.error(e);
                Thread.currentThread().interrupt();
            }
        }
        if (queue.size() == capacity) {
            notifyAll();
        }
        return queue.remove();
    }

    public synchronized int size() {
        return queue.size();
    }
}
