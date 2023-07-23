package utils;

import java.util.Random;

public class RandomGenerator {
    public static int getRandomInt(){
        Random rand = new Random();
        int upperbound = 1000;
        return rand.nextInt(upperbound);
    }
}
