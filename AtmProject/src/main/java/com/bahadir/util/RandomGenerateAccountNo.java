package com.bahadir.util;

import java.util.Random;

public class RandomGenerateAccountNo {

    public static String generateAccountNo(){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

}
