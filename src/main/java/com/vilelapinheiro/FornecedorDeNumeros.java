package com.vilelapinheiro;

import java.util.Random;

public class FornecedorDeNumeros {
    Random generator;

    public FornecedorDeNumeros() {
        this(new Random());
    }

    public FornecedorDeNumeros(Random generator) {
        this.generator = generator;
    }

    public int giveMeANumberBetweenInclusive(int begin, int end) {
        return generator.ints(begin, end + 1).iterator().nextInt();
    }
}
