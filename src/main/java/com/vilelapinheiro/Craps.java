package com.vilelapinheiro;

import java.util.Scanner;

public class Craps {
    public static void main(String... args) {
        Craps craps = new Craps();

        try(Scanner scan=new Scanner(System.in)) {
            while (!craps.isFimDeJogo()) {
                System.out.println("Digite enter para continuar");
                scan.nextLine();
                int soma = craps.rolarDados();
                System.out.println("Soma: " + soma);
            }
        }

        if (craps.getVencedor() == 1) {
            System.out.println("Você ganhou!");
        } else if (craps.getVencedor() == 2) {
            System.out.println("A mesa ganhou!");
        } else {
            throw new IllegalStateException();
        }
    }


    private int soma, ponto, vencedor;
    private boolean primeiraRodada = true;
    private Dado dado = new Dado();

    public boolean isFimDeJogo() {
        return vencedor == 1 || vencedor == 2;
    }

    public int getVencedor() {
        return vencedor;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }

    public int getPonto() {
        return ponto;
    }

    public int rolarDados() {
        int dado1 = dado.rolar();
        int dado2 = dado.rolar();
        soma = dado1 + dado2;


        if (primeiraRodada) {
            if (soma == 7 || soma == 11) {
                vencedor = 1;
//                System.out.println("Primeira rodada Dados " + dado1 + " e " + dado2 + " com soma " + soma + " vencedor " + vencedor);
            } else if (soma == 2 || soma == 3 || soma == 12) {
                vencedor = 2;
//                System.out.println("Primeira rodada Dados " + dado1 + " e " + dado2 + " com soma " + soma + " vencedor " + vencedor);
            } else {
                ponto = soma;
//                System.out.println("Primeira rodada Dados " + dado1 + " e " + dado2 + " com soma " + soma + " sem vencedor por enquanto");
            }
            primeiraRodada = false;
        } else {
            if (soma == 7) {
                vencedor = 2;
//                System.out.println("Rodada subsequente Dados " + dado1 + " e " + dado2 + " com soma " + soma + " vencedor " + vencedor);
            } else if (soma == ponto) {
                vencedor = 1;
//                System.out.println("Rodada subsequente Dados " + dado1 + " e " + dado2 + " com soma " + soma + " vencedor " + vencedor);
            } else {
                //ninguem venceu ainda
//                System.out.println("Rodada subsequente Dados " + dado1 + " e " + dado2 + " com soma " + soma + " sem vencedor por enquanto");
            }
        }
        return soma;
    }
}
