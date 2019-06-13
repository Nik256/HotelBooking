package com.epam.concurrency.generator;

import java.time.LocalDate;
import java.util.Random;

public class RequestGenerator {

    private static final Random random = new Random();

    public static LocalDate getRandomLocalDate() {
        long minDay = LocalDate.now().toEpochDay();
        long maxDay = LocalDate.of(2025, 12, 31).toEpochDay();
        long randomDay = random.longs(minDay, (maxDay + 1)).findFirst().getAsLong();
        return LocalDate.ofEpochDay(randomDay);
    }

    public static String getRandomString(int stringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        StringBuilder buffer = new StringBuilder(stringLength);
        for (int i = 0; i < stringLength; i++) {
            int randomLimitedInt = leftLimit +
                    (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static int getRandomNumberInRange(int min, int max) {
        return random.ints(min, (max + 1))
                .findFirst()
                .getAsInt();
    }
}
