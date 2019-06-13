package com.epam.concurrency.main;

import com.epam.concurrency.concurrent.Consumer;
import com.epam.concurrency.concurrent.Producer;
import com.epam.concurrency.pojo.HotelBookingRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        int numProducers = 3;
        int numConsumers = 6;

        BlockingQueue myQueue = new LinkedBlockingQueue<HotelBookingRequest>(5);

        for (int i = 0; i < numProducers; i++){
            new Thread(new Producer(myQueue, i)).start();
        }

        for (int i = 0; i < numConsumers; i++){
            new Thread(new Consumer(myQueue, i)).start();
        }
    }
}
