package com.vilelapinheiro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import static com.vilelapinheiro.TipoTriangulo.*;

public class Triangulo {
    public static final String ESTE_TRIÂNGULO_É_IMPOSSÍVEL_DE_EXISTIR = "Este triângulo é impossível de existir";
    public static final String NÃO_PODE_HAVER_UM_LADO_MENOR_OU_IGUAL_A_ZERO = "Não pode haver um lado menor ou igual a zero";
    public static final String ESTE_NÃO_É_UM_TRIÂNGULO_ISÓSCELES = "Este não é um triângulo isósceles";
    private BigDecimal isolescelesLadosIguais, isolescelesLadoDiferente;
    private BigDecimal menorLado, medioLado, maiorLado;
    private TipoTriangulo tipoTriangulo;

    public BigDecimal getMaiorLado() {
        return maiorLado;
    }

    public BigDecimal getMedioLado() {
        return medioLado;
    }

    public BigDecimal getMenorLado() {
        return menorLado;
    }

    public TipoTriangulo getTipoTriangulo() {
        return tipoTriangulo;
    }

    public BigDecimal getIsolescelesLadosIguais() {
        if (tipoTriangulo != ISOSCELES) {
            throw new TrianguloInvalido(ESTE_NÃO_É_UM_TRIÂNGULO_ISÓSCELES);
        }
        return isolescelesLadosIguais;
    }

    public BigDecimal getIsolescelesLadoDiferente() {
        if (tipoTriangulo != ISOSCELES) {
            throw new TrianguloInvalido(ESTE_NÃO_É_UM_TRIÂNGULO_ISÓSCELES);
        }
        return isolescelesLadoDiferente;
    }

    public Triangulo(BigDecimal ladoA, BigDecimal ladoB, BigDecimal ladoC) {

        determinarOsLados(ladoA, ladoB, ladoC);

        validarAPossibilidadeDoTriangulo(ladoA, ladoB, ladoC);

        determinarOTipoDeTriangulo();

        System.out.println("Temos um triângulo do tipo " + tipoTriangulo + " com as medidas: " + menorLado + " " + medioLado + " " + maiorLado);

    }

    private void validarAPossibilidadeDoTriangulo(BigDecimal ladoA, BigDecimal ladoB, BigDecimal ladoC) {
        if (BigDecimal.ZERO.compareTo(ladoA) >= 0
                || BigDecimal.ZERO.compareTo(ladoB) >= 0
                || BigDecimal.ZERO.compareTo(ladoC) >= 0) {
            throw new TrianguloInvalido(NÃO_PODE_HAVER_UM_LADO_MENOR_OU_IGUAL_A_ZERO);
        }

        BigDecimal somaDosLadosMenores = medioLado.add(menorLado);
        if (somaDosLadosMenores.compareTo(maiorLado) <= 0) {
            throw new TrianguloInvalido(ESTE_TRIÂNGULO_É_IMPOSSÍVEL_DE_EXISTIR);
        }
    }
    private void determinarOTipoDeTriangulo() {
        if (menorLado.compareTo(medioLado) == 0 && menorLado.compareTo(maiorLado) == 0) {
            tipoTriangulo = EQUILATERO;
        } else if (menorLado.compareTo(medioLado) == 0) {
            isolescelesLadosIguais = menorLado;
            isolescelesLadoDiferente = maiorLado;
            tipoTriangulo = ISOSCELES;
        } else if (maiorLado.compareTo(medioLado) == 0) {
            isolescelesLadosIguais = maiorLado;
            isolescelesLadoDiferente = menorLado;
            tipoTriangulo = ISOSCELES;
        } else {
            tipoTriangulo = ESCALENO;
        }
    }

    private void determinarOsLados(BigDecimal ladoA, BigDecimal ladoB, BigDecimal ladoC) {
        var lista = new ArrayList<BigDecimal>();
        lista.add(ladoA);
        lista.add(ladoB);
        lista.add(ladoC);
        Collections.sort(lista);
        menorLado = lista.get(0);
        medioLado = lista.get(1);
        maiorLado = lista.get(2);
    }
}
