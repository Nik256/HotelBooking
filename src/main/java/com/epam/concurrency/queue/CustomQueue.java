package com.epam.concurrency.queue;

import com.epam.concurrency.model.HotelBookingRequest;
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

    public synchronized void put(HotelBookingRequest request) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(request);
        notifyAll();
    }

    public synchronized HotelBookingRequest take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        notifyAll();
        return queue.remove();
    }
}
