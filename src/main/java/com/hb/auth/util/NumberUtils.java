package com.hb.auth.util;

import java.util.Random;

public class NumberUtils {

    /**
     * Generate 4 digits integer number
     * @return Integer
     */
    public static int Generate4DigitsNumber() {
        Random rand = new Random();
        return rand.nextInt(8999) + 1000;
    }

    /**
     * Generate 6 digits integer number
     * @return Integer
     */
    public static int Generate6DigitsNumber() {
        Random rand = new Random();
        return rand.nextInt(899999) + 100000;
    }

}
