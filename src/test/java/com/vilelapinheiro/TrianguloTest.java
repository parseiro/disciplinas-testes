package com.vilelapinheiro;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.vilelapinheiro.TipoTriangulo.*;
import static com.vilelapinheiro.Triangulo.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TrianguloTest {

    @Test
    public void testaValoresZerosENegativos() {
        var listaDeTriplas = List.of(

                // Um valor zero
                new Triplet<>(BigDecimal.valueOf(0), BigDecimal.valueOf(3), BigDecimal.valueOf(5)),

                // Um CT para os três valores iguais a zero
                new Triplet<>(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0)),

                // Um valor negativo
                new Triplet<>(BigDecimal.valueOf(5), BigDecimal.valueOf(-1), BigDecimal.valueOf(2))
        );

        for (var triplet : listaDeTriplas) {
            assertThatThrownBy(() -> {
                new Triangulo(triplet.getValue1(), triplet.getValue2(), triplet.getValue3());
            })
                    .isInstanceOf(TrianguloInvalido.class)
                    .hasMessageContaining(NÃO_PODE_HAVER_UM_LADO_MENOR_OU_IGUAL_A_ZERO);
        }


        assertThatThrownBy(() -> {
            new Triangulo(BigDecimal.valueOf(-2), BigDecimal.valueOf(3), BigDecimal.valueOf(5));
        })
                .isInstanceOf(TrianguloInvalido.class)
                .hasMessageContaining(NÃO_PODE_HAVER_UM_LADO_MENOR_OU_IGUAL_A_ZERO);
    }

    @Test
    public void testarTriangulosImpossiveis() {

        var listaDeTriplas = List.of(

                // A soma de 2 lados é exatamente igual ao terceiro lado
                // Para o item acima, um CT para cada permutação de valores
                new Triplet<>(BigDecimal.valueOf(2), BigDecimal.valueOf(3), BigDecimal.valueOf(5)),
                new Triplet<>(BigDecimal.valueOf(3), BigDecimal.valueOf(2), BigDecimal.valueOf(5)),
                new Triplet<>(BigDecimal.valueOf(5), BigDecimal.valueOf(2), BigDecimal.valueOf(3)),
                new Triplet<>(BigDecimal.valueOf(5), BigDecimal.valueOf(3), BigDecimal.valueOf(2)),
                new Triplet<>(BigDecimal.valueOf(2), BigDecimal.valueOf(5), BigDecimal.valueOf(3)),
                new Triplet<>(BigDecimal.valueOf(3), BigDecimal.valueOf(5), BigDecimal.valueOf(2)),


                // CT em que a soma de 2 lados é menor que o terceiro lado
                // Para o item acima, um CT para cada permutação de valores
                new Triplet<>(BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(5.0)),
                new Triplet<>(BigDecimal.valueOf(2), BigDecimal.valueOf(1), BigDecimal.valueOf(5.0)),
                new Triplet<>(BigDecimal.valueOf(2), BigDecimal.valueOf(5), BigDecimal.valueOf(1)),
                new Triplet<>(BigDecimal.valueOf(1), BigDecimal.valueOf(5), BigDecimal.valueOf(2)),
                new Triplet<>(BigDecimal.valueOf(5), BigDecimal.valueOf(2), BigDecimal.valueOf(1)),
                new Triplet<>(BigDecimal.valueOf(5), BigDecimal.valueOf(1), BigDecimal.valueOf(2)),


                new Triplet<>(BigDecimal.valueOf(1.9), BigDecimal.valueOf(3), BigDecimal.valueOf(4.9))
        );

        for (var triplet : listaDeTriplas) {
            assertThatThrownBy(() -> {
                new Triangulo(triplet.getValue1(), triplet.getValue2(), triplet.getValue3());
            })
                    .isInstanceOf(TrianguloInvalido.class)
                    .hasMessageContaining(ESTE_TRIÂNGULO_É_IMPOSSÍVEL_DE_EXISTIR);
        }
    }

    @Test
    public void testarTriangulosEquilateros() throws TrianguloInvalido {
        //Triângulo equilatero válido

        var listaDeTriplas = List.of(
                new Triplet<>(BigDecimal.valueOf(0.0001), BigDecimal.valueOf(0.0001), BigDecimal.valueOf(0.0001)),
                new Triplet<>(BigDecimal.valueOf(1.11), BigDecimal.valueOf(1.11), BigDecimal.valueOf(1.11)),
                new Triplet<>(BigDecimal.valueOf(299.05), BigDecimal.valueOf(299.05), BigDecimal.valueOf(299.05))
        );

        for (var triplet : listaDeTriplas) {

            Triangulo triangulo = new Triangulo(triplet.getValue1(), triplet.getValue2(), triplet.getValue3());

            assertThat(triangulo.getMenorLado()).isEqualTo(triangulo.getMedioLado());
            assertThat(triangulo.getMenorLado()).isEqualTo(triangulo.getMaiorLado());
            assertThat(triangulo.getMedioLado()).isEqualTo(triangulo.getMaiorLado());
            assertThat(triangulo.getTipoTriangulo()).isEqualTo(EQUILATERO);

            garantirQueNaoEhIsosceles(triangulo);
        }
    }

    @Test
    public void testarTriangulosIsosceles() {
        //Triângulo isósceles válido


        var listaDeTriplas = List.of(
                new Triplet<>(BigDecimal.valueOf(2.5), BigDecimal.valueOf(2.5), BigDecimal.valueOf(3.1)),

                //Pelo menos 3 casos de teste (CTs) para isósceles válido contendo a permutação dos mesmos valores
                new Triplet<>(BigDecimal.valueOf(4.4), BigDecimal.valueOf(4.4), BigDecimal.valueOf(1.6)),
                new Triplet<>(BigDecimal.valueOf(4.4), BigDecimal.valueOf(1.6), BigDecimal.valueOf(4.4)),
                new Triplet<>(BigDecimal.valueOf(1.6), BigDecimal.valueOf(4.4), BigDecimal.valueOf(4.4)),

                //Pelo menos 3 casos de teste (CTs) para isósceles válido contendo a permutação dos mesmos valores
                new Triplet<>(BigDecimal.valueOf(4.9), BigDecimal.valueOf(3.3), BigDecimal.valueOf(3.3)),
                new Triplet<>(BigDecimal.valueOf(3.3), BigDecimal.valueOf(4.9), BigDecimal.valueOf(3.3)),
                new Triplet<>(BigDecimal.valueOf(3.3), BigDecimal.valueOf(3.3), BigDecimal.valueOf(4.9))
        );

        for (var triplet : listaDeTriplas) {

            Triangulo triangulo = new Triangulo(triplet.getValue1(), triplet.getValue2(), triplet.getValue3());

            BigDecimal tamanhoDosLadosIguais;
            BigDecimal tamanhoDoLadoDiferente;

            {
                var list = new ArrayList<BigDecimal>();
                list.add(triplet.getValue1());
                list.add(triplet.getValue2());
                list.add(triplet.getValue3());
                Collections.sort(list);

                BigDecimal menor = list.get(0);
                BigDecimal doMeio = list.get(1);
                BigDecimal maior = list.get(2);


                if (doMeio.compareTo(maior) == 0) {
                    tamanhoDosLadosIguais = doMeio;
                    tamanhoDoLadoDiferente = menor;
                } else {
                    tamanhoDosLadosIguais = doMeio;
                    tamanhoDoLadoDiferente = maior;
                }
            }

            assertThat(triangulo.getTipoTriangulo()).isEqualTo(ISOSCELES);
            assertThat(triangulo.getIsolescelesLadosIguais()).isEqualTo(tamanhoDosLadosIguais);
            assertThat(triangulo.getIsolescelesLadoDiferente()).isEqualTo(tamanhoDoLadoDiferente);
        }
    }

    @Test
    public void testarTriangulosEscalenos() {
        // Triângulo escaleno válido
        var listaDeTriplas = List.of(
                new Triplet<>(BigDecimal.valueOf(1.6), BigDecimal.valueOf(3.3), BigDecimal.valueOf(4.8)),
                new Triplet<>(BigDecimal.valueOf(3.3), BigDecimal.valueOf(1.6), BigDecimal.valueOf(3.4))
        );

        for (var triplet : listaDeTriplas) {

            Triangulo triangulo = new Triangulo(triplet.getValue1(), triplet.getValue2(), triplet.getValue3());

            assertThat(triangulo.getTipoTriangulo()).isEqualTo(ESCALENO);

            garantirQueNaoEhIsosceles(triangulo);
        }
    }

    private void garantirQueNaoEhIsosceles(Triangulo triangulo) {
        // testar que ele não é isósceles #1
        assertThatThrownBy(() -> {
            var a = triangulo.getIsolescelesLadoDiferente();
        })
                .isInstanceOf(TrianguloInvalido.class)
                .hasMessageContaining(ESTE_NÃO_É_UM_TRIÂNGULO_ISÓSCELES);

        // testar que ele não é isósceles #2
        assertThatThrownBy(() -> {
            var a = triangulo.getIsolescelesLadosIguais();
        })
                .isInstanceOf(TrianguloInvalido.class)
                .hasMessageContaining(ESTE_NÃO_É_UM_TRIÂNGULO_ISÓSCELES);
    }
}
