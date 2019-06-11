package com.epam.concurrency.generator;

import java.time.LocalDate;
import java.util.Random;

public class RequestGenerator {

    public static LocalDate getRandomLocalDate() {
        long minDay = LocalDate.now().toEpochDay();
        long maxDay = LocalDate.of(2025, 12, 31).toEpochDay();
        Random random = new Random();
        long randomDay = random.longs(minDay, (maxDay + 1)).findFirst().getAsLong();
        return LocalDate.ofEpochDay(randomDay);
    }

    public static String getRandomString(int stringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(stringLength);
        for (int i = 0; i < stringLength; i++) {
            int randomLimitedInt = leftLimit +
                    (random.nextInt() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.ints(min, (max + 1))
                .findFirst()
                .getAsInt();
    }
}
