package com.vilelapinheiro;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Pair<T> {
    private T value1, value2;

    public Pair(T value1, T value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public T getValue1() {
        return value1;
    }

    public T getValue2() {
        return value2;
    }
}

class CrapsTest {

    @Test
    void testarMesaVencendoNaPrimeiraJogada() {
        Dado dadoMock = Mockito.mock(Dado.class);

        List<Pair<Integer>> listDePares = List.of(
                // Soma = 2
                new Pair(1, 1),

                // Soma = 3
                new Pair(1, 2),
                new Pair(2, 1),

                // Soma = 12
                new Pair(6, 6)
        );

        for (var par : listDePares) {
            Craps craps = new Craps();
            craps.setDado(dadoMock);

            Mockito.when(dadoMock.rolar()).thenReturn(par.getValue1(), par.getValue2());

            craps.rolarDados();

            assertThat(craps.isFimDeJogo()).isTrue();
            assertThat(craps.getVencedor()).isEqualTo(2);
        }
    }

    @Test
    void testarJogadorVencendoNaPrimeiraJogada() {
        Dado dadoMock = Mockito.mock(Dado.class);

        List<Pair<Integer>> listDePares = List.of(
                new Pair(4, 3),
                new Pair(3, 4),
                new Pair(5, 6),
                new Pair(6, 5)
        );

        for (var par : listDePares) {
            Craps craps = new Craps();
            craps.setDado(dadoMock);

            Mockito.when(dadoMock.rolar()).thenReturn(par.getValue1(), par.getValue2());

            craps.rolarDados();

            assertThat(craps.isFimDeJogo()).isTrue();
            assertThat(craps.getVencedor()).isEqualTo(1);
        }
    }

    @Test
    void testarJogadorPerdendoNaSegundaJogada() {
        Dado dadoMock = Mockito.mock(Dado.class);

        List<Pair<Integer>> listDePares = List.of(
                // Soma = 6
                new Pair(1, 5),
                new Pair(5, 1),
                new Pair(4, 2),
                new Pair(2, 4),
                new Pair(3, 3),

                // Soma = 8
                new Pair(4, 4),
                new Pair(3, 5),
                new Pair(5, 3),
                new Pair(2, 6),
                new Pair(6, 2),

                // Soma = 9
                new Pair(4, 5),
                new Pair(5, 4),
                new Pair(3, 6),
                new Pair(6, 3),

                // Soma = 10
                new Pair(5, 5),
                new Pair(4, 6),
                new Pair(6, 4)
        );

        List<Pair<Integer>> listDeParesSoma7 = List.of(
                // 7
                new Pair(1, 6),
                new Pair(6, 1),
                new Pair(2, 5),
                new Pair(5, 2),
                new Pair(3, 4),
                new Pair(4, 3)
        );

        for (var par : listDePares) {
            for (var par2 : listDeParesSoma7) {
                Craps craps = new Craps();
                craps.setDado(dadoMock);

                Mockito.when(dadoMock.rolar()).thenReturn(par.getValue1(), par.getValue2());

                craps.rolarDados();

                Mockito.when(dadoMock.rolar()).thenReturn(par2.getValue1(), par2.getValue2());

                craps.rolarDados();

                assertThat(craps.isFimDeJogo()).isTrue();
                assertThat(craps.getVencedor()).isEqualTo(2);
            }
        }
    }

    @Test
    void testarJogadorVencendoEmJogadaSubsequente() {
        Dado dadoMock = Mockito.mock(Dado.class);

        List<Pair<Integer>> listDePares = List.of(
                // Soma = 6
                new Pair(1, 5),
                new Pair(5, 1),
                new Pair(4, 2),
                new Pair(2, 4),
                new Pair(3, 3),

                // Soma = 8
                new Pair(4, 4),
                new Pair(3, 5),
                new Pair(5, 3),
                new Pair(2, 6),
                new Pair(6, 2),

                // Soma = 9
                new Pair(4, 5),
                new Pair(5, 4),
                new Pair(3, 6),
                new Pair(6, 3),

                // Soma = 10
                new Pair(5, 5),
                new Pair(4, 6),
                new Pair(6, 4)
        );

        for (var par : listDePares) {
            for (var par2 : listDePares) {
                Craps craps = new Craps();
                craps.setDado(dadoMock);

                Mockito.when(dadoMock.rolar()).thenReturn(par.getValue1(), par.getValue2());

                craps.rolarDados();

                Mockito.when(dadoMock.rolar()).thenReturn(par2.getValue1(), par2.getValue2());

                craps.rolarDados();

                final int somaInicial = par.getValue1() + par.getValue2();
                final int somaFinal = par2.getValue1() + par2.getValue2();
//                System.out.println("Pontos: " + craps.getPonto() + " tentativa atual: " + somaFinal);

                if (somaInicial == somaFinal) {
                    assertThat(craps.isFimDeJogo()).isTrue();
                    assertThat(craps.getVencedor()).isEqualTo(1);
                } else {
                    assertThat(craps.isFimDeJogo()).isFalse();
                }

            }
        }
    }


}