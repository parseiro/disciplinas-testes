package com.vilelapinheiro;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioTest {

    @Test
    void testaSalarioDesenvolvedorExatamente3000() {
        BigDecimal salarioTestado = BigDecimal.valueOf(3000);
        Cargo cargoTestado = Cargo.DESENVOLVEDOR;
        BigDecimal salarioLiquidoDeveSer = BigDecimal.valueOf(2400.00).setScale(2, RoundingMode.HALF_EVEN);

        Funcionario newFuncionario = new Funcionario("Leonardo", "email@gmail.com", salarioTestado, cargoTestado);
        assertEquals(0, newFuncionario.getSalarioLiquido().compareTo(salarioLiquidoDeveSer));
    }

    @Test
    void testaSalarioDesenvolvedorMaiorQue3000() {
        BigDecimal salarioTestado = BigDecimal.valueOf(3001);
        Cargo cargoTestado = Cargo.DESENVOLVEDOR;
        BigDecimal salarioLiquidoDeveSer = BigDecimal.valueOf(2400.80).setScale(2, RoundingMode.HALF_EVEN);

        Funcionario newFuncionario = new Funcionario("Leonardo", "email@gmail.com", salarioTestado, cargoTestado);
        assertEquals(0, newFuncionario.getSalarioLiquido().compareTo(salarioLiquidoDeveSer));
    }

    @Test
    void testaSalarioDesenvolvedorMenorQue3000() {
        BigDecimal salarioTestado = BigDecimal.valueOf(2999);
        Cargo cargoTestado = Cargo.DESENVOLVEDOR;
        BigDecimal salarioLiquidoDeveSer = BigDecimal.valueOf(2549.15).setScale(2, RoundingMode.HALF_EVEN);

        Funcionario newFuncionario = new Funcionario("Leonardo", "email@gmail.com", salarioTestado, cargoTestado);
        assertEquals(0, newFuncionario.getSalarioLiquido().compareTo(salarioLiquidoDeveSer));
    }

    @Test
    void testaSalarioDesenvolvedorExatamente5000() {
        BigDecimal salarioTestado = BigDecimal.valueOf(5000);
        Cargo cargoTestado = Cargo.DESENVOLVEDOR;
        BigDecimal salarioLiquidoDeveSer = BigDecimal.valueOf(4000.00).setScale(2, RoundingMode.HALF_EVEN);

        Funcionario newFuncionario = new Funcionario("Leonardo", "email@gmail.com", salarioTestado, cargoTestado);
        assertEquals(0, newFuncionario.getSalarioLiquido().compareTo(salarioLiquidoDeveSer));
    }

    @Test
    void testaSalarioDBAExatamente2000() {
        BigDecimal salarioTestado = BigDecimal.valueOf(2000);
        Cargo cargoTestado = Cargo.DBA;
        BigDecimal salarioLiquidoDeveSer = BigDecimal.valueOf(1500).setScale(2, RoundingMode.HALF_EVEN);

        Funcionario newFuncionario = new Funcionario("Leonardo", "email@gmail.com", salarioTestado, cargoTestado);
        assertEquals(0, newFuncionario.getSalarioLiquido().compareTo(salarioLiquidoDeveSer));
    }

    @Test
    void testaSalarioDBAMaiorQue2000() {
        BigDecimal salarioTestado = BigDecimal.valueOf(2001);
        Cargo cargoTestado = Cargo.DBA;
        BigDecimal salarioLiquidoDeveSer = BigDecimal.valueOf(1500.75).setScale(2, RoundingMode.HALF_EVEN);

        Funcionario newFuncionario = new Funcionario("Leonardo", "email@gmail.com", salarioTestado, cargoTestado);
        assertEquals(0, newFuncionario.getSalarioLiquido().compareTo(salarioLiquidoDeveSer));
    }

    @Test
    void testaSalarioDBAMenorQue2000() {
        BigDecimal salarioTestado = BigDecimal.valueOf(1999);
        Cargo cargoTestado = Cargo.DBA;
        BigDecimal salarioLiquidoDeveSer = BigDecimal.valueOf(1699.15).setScale(2, RoundingMode.HALF_EVEN);

        Funcionario newFuncionario = new Funcionario("Leonardo", "email@gmail.com", salarioTestado, cargoTestado);
        assertEquals(0, newFuncionario.getSalarioLiquido().compareTo(salarioLiquidoDeveSer));
    }

    @Test
    void testaSalarioTestadorExatamente2000() {
        BigDecimal salarioTestado = BigDecimal.valueOf(2000);
        Cargo cargoTestado = Cargo.TESTADOR;
        BigDecimal salarioLiquidoDeveSer = BigDecimal.valueOf(1500).setScale(2, RoundingMode.HALF_EVEN);

        Funcionario newFuncionario = new Funcionario("Leonardo", "email@gmail.com", salarioTestado, cargoTestado);
        assertEquals(0, newFuncionario.getSalarioLiquido().compareTo(salarioLiquidoDeveSer));
    }

    @Test
    void testaSalarioTestadorMaiorQue2000() {
        BigDecimal salarioTestado = BigDecimal.valueOf(2001);
        Cargo cargoTestado = Cargo.TESTADOR;
        BigDecimal salarioLiquidoDeveSer = BigDecimal.valueOf(1500.75).setScale(2, RoundingMode.HALF_EVEN);

        Funcionario newFuncionario = new Funcionario("Leonardo", "email@gmail.com", salarioTestado, cargoTestado);
        assertEquals(0, newFuncionario.getSalarioLiquido().compareTo(salarioLiquidoDeveSer));
    }

    @Test
    void testaSalarioTestadorMenorQue2000() {
        BigDecimal salarioTestado = BigDecimal.valueOf(1999);
        Cargo cargoTestado = Cargo.TESTADOR;
        BigDecimal salarioLiquidoDeveSer = BigDecimal.valueOf(1699.15).setScale(2, RoundingMode.HALF_EVEN);

        Funcionario newFuncionario = new Funcionario("Leonardo", "email@gmail.com", salarioTestado, cargoTestado);
        assertEquals(0, newFuncionario.getSalarioLiquido().compareTo(salarioLiquidoDeveSer));
    }

    @Test
    void testaSalarioTestadorExatamente550() {
        BigDecimal salarioTestado = BigDecimal.valueOf(550);
        Cargo cargoTestado = Cargo.TESTADOR;
        BigDecimal salarioLiquidoDeveSer = BigDecimal.valueOf(467.50).setScale(2, RoundingMode.HALF_EVEN);

        Funcionario newFuncionario = new Funcionario("Leonardo", "email@gmail.com", salarioTestado, cargoTestado);
        assertEquals(0, newFuncionario.getSalarioLiquido().compareTo(salarioLiquidoDeveSer));
    }

    @Test
    void testaSalarioGerenteExatamente5000() {
        BigDecimal salarioTestado = BigDecimal.valueOf(5000);
        Cargo cargoTestado = Cargo.GERENTE;
        BigDecimal salarioLiquidoDeveSer = BigDecimal.valueOf(3500).setScale(2, RoundingMode.HALF_EVEN);

        Funcionario newFuncionario = new Funcionario("Leonardo", "email@gmail.com", salarioTestado, cargoTestado);
        assertEquals(0, newFuncionario.getSalarioLiquido().compareTo(salarioLiquidoDeveSer));
    }

    @Test
    void testaSalarioGerenteExatamente2500() {
        BigDecimal salarioTestado = BigDecimal.valueOf(2500);
        Cargo cargoTestado = Cargo.GERENTE;
        BigDecimal salarioLiquidoDeveSer = BigDecimal.valueOf(2000).setScale(2, RoundingMode.HALF_EVEN);

        Funcionario newFuncionario = new Funcionario("Leonardo", "email@gmail.com", salarioTestado, cargoTestado);
        assertEquals(0, newFuncionario.getSalarioLiquido().compareTo(salarioLiquidoDeveSer));
    }

    @Test
    void testaSalarioGerenteMaiorQue5000() {
        BigDecimal salarioTestado = BigDecimal.valueOf(5001);
        Cargo cargoTestado = Cargo.GERENTE;
        BigDecimal salarioLiquidoDeveSer = BigDecimal.valueOf(3500.7).setScale(2, RoundingMode.HALF_EVEN);

        Funcionario newFuncionario = new Funcionario("Leonardo", "email@gmail.com", salarioTestado, cargoTestado);
        assertEquals(0, newFuncionario.getSalarioLiquido().compareTo(salarioLiquidoDeveSer));
    }

    @Test
    void testaSalarioGerenteMenorQue5000() {
        BigDecimal salarioTestado = BigDecimal.valueOf(4999);
        Cargo cargoTestado = Cargo.GERENTE;
        BigDecimal salarioLiquidoDeveSer = BigDecimal.valueOf(3999.2).setScale(2, RoundingMode.HALF_EVEN);

        Funcionario newFuncionario = new Funcionario("Leonardo", "email@gmail.com", salarioTestado, cargoTestado);
        assertEquals(0, newFuncionario.getSalarioLiquido().compareTo(salarioLiquidoDeveSer));
    }

}