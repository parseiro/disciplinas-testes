package com.vilelapinheiro;

public class MyRandomNumber {
    public static final String NUMEROS_REPETIDOS = "Numeros repetidos";
    public static final String NUMERO_GERADO_FORA_DA_FAIXA = "Numero gerado fora da faixa";
    public static final String INTERVALO_PEDIDO_INVALIDO = "Intervalo pedido invalido";
    final private FornecedorDeNumeros random;
    private Integer previousNumber;

    public MyRandomNumber() {
        this(new FornecedorDeNumeros());
    }

    public MyRandomNumber(FornecedorDeNumeros random) {
        this.random = random;
    }

    /**
     * @param begin inicio do intervalo
     * @param end   fim do intervalo
     * @return retornar um numero aleatorio entre [begin, end]
     * o numero aleatorio retornado nao pode ser igual ao anterior
     * @throws IntervaloInvalidoException essa excecao eh lancada quando begin >= end ou (begin<0 || end<0)
     * @throws BugNoGeradorAleatorio se os numeros gerados forem sempre iguais ou fora do intervalo
     */

    public int nextRandomNumber(int begin, int end) throws IntervaloInvalidoException, BugNoGeradorAleatorio {

        if (begin < 0 || begin >= end) {
            throw new IntervaloInvalidoException(INTERVALO_PEDIDO_INVALIDO);
        }

        int number;

        if (previousNumber == null) {
            number = random.giveMeANumberBetweenInclusive(begin, end);
        } else {
            number = previousNumber;
            int tentativa = 0;
            while (number == previousNumber) {
                tentativa++;
                if (tentativa == 10) {
                    throw new BugNoGeradorAleatorio(NUMEROS_REPETIDOS);
                }
                number = random.giveMeANumberBetweenInclusive(begin, end);
            }
        }

        if (number < begin || number > end) {
            throw new BugNoGeradorAleatorio(NUMERO_GERADO_FORA_DA_FAIXA);
        }

        previousNumber = number;
        return number;
    }
}