package com.vilelapinheiro;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.vilelapinheiro.MyRandomNumber.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MyRandomNumberTest {

    FornecedorDeNumeros fornecedorMock = Mockito.mock(FornecedorDeNumeros.class);

    @Test
    void simulaIntervalosInvalidos() {
        List<Pair<Integer>> tuplesList = List.of(
                new Pair(-1, 1), // Begin < 0
                new Pair(25, 25), // No numbers between
                new Pair(3, 2), // end smaller than begin
                new Pair(2, -1) // end < 0
        );

        MyRandomNumber generator = new MyRandomNumber(new FornecedorDeNumeros());

        for (var tuple : tuplesList) {
            assertThatThrownBy(() -> {
                generator.nextRandomNumber(tuple.getValue1(), tuple.getValue2());
            })
                    .isInstanceOf(IntervaloInvalidoException.class)
                    .hasMessageContaining(INTERVALO_PEDIDO_INVALIDO);
        }
    }

    @Test
    void simulaGeradorGerandoNumerosRepetidos() {
        final int INTERVAL_START = 1, INTERVAL_END = 5, FIXED_RETURN = 3;

        Mockito.when(fornecedorMock.giveMeANumberBetweenInclusive(INTERVAL_START, INTERVAL_END)).thenReturn(FIXED_RETURN);

        MyRandomNumber generator = new MyRandomNumber(fornecedorMock);

        try {
            generator.nextRandomNumber(INTERVAL_START, INTERVAL_END);
        } catch (IntervaloInvalidoException | BugNoGeradorAleatorio e) {
            e.printStackTrace();
        }

        assertThatThrownBy(() -> {
            generator.nextRandomNumber(INTERVAL_START, INTERVAL_END);
        })
                .isInstanceOf(BugNoGeradorAleatorio.class)
                .hasMessageContaining(NUMEROS_REPETIDOS);

    }

    @Test
    void simulaGeradorGerandoNumerosForaDaFaixa() {
        List<Triplet<Integer>> listDeTuplas = List.of(
                new Triplet(1, 2, 3),
                new Triplet(4, 5, 3)
        );

        MyRandomNumber generator = new MyRandomNumber(fornecedorMock);

        for (var tupla : listDeTuplas) {
            Mockito.when(fornecedorMock.giveMeANumberBetweenInclusive(tupla.getValue1(), tupla.getValue2())).thenReturn(tupla.getValue3());

            assertThatThrownBy(() -> {
                generator.nextRandomNumber(tupla.getValue1(), tupla.getValue2());
            })
                    .isInstanceOf(BugNoGeradorAleatorio.class)
                    .hasMessageContaining(NUMERO_GERADO_FORA_DA_FAIXA)
            ;

        }

    }
}