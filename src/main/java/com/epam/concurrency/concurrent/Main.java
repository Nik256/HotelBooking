package com.epam.concurrency.concurrent;

import com.epam.concurrency.generator.RequestGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        logger.info(RequestGenerator.getRandomString(10));
    }
}
