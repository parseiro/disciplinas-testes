package com.vilelapinheiro;

import java.util.Random;

public class Dado {
    private Random rand = new Random();
    public int rolar() {
        return rand.nextInt(6) + 1;
    }
}
