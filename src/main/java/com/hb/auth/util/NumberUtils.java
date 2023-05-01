package com.hb.auth.util;

import java.util.Random;

public class NumberUtils {

    /**
     * Generate 4 digits integer number
     * @return Integer
     */
    public static Integer Generate4DigitsNumber() {
        return new Random().nextInt(8999) + 1000;
    }

    /**
     * Generate 6 digits integer number
     * @return Integer
     */
    public static Integer Generate6DigitsNumber() {
        return new Random().nextInt(899999) + 100000;
    }

}
