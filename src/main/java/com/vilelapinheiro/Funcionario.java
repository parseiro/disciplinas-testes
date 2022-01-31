package com.vilelapinheiro;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Funcionario {
    private String nome;
    private String email;
    private BigDecimal salario;
    private Cargo cargo;

    public Funcionario(String nome, String email, BigDecimal salario, Cargo cargo) {
        this.nome = nome;
        this.email = email;
        this.setSalario(salario);
        this.cargo = cargo;
    }

    public BigDecimal getSalarioLiquido() {
        BigDecimal taxa = BigDecimal.ZERO;

        switch (cargo) {
            case DESENVOLVEDOR:
                taxa = salario.compareTo(BigDecimal.valueOf(3000)) >= 0 ? BigDecimal.valueOf(0.20) : BigDecimal.valueOf(0.15);
                break;
            case DBA:
            case TESTADOR:
                taxa = salario.compareTo(BigDecimal.valueOf(2000)) >= 0 ? BigDecimal.valueOf(0.25) : BigDecimal.valueOf(0.15);
                break;
            case GERENTE:
                taxa = salario.compareTo(BigDecimal.valueOf(5000)) >= 0 ? BigDecimal.valueOf(0.3) : BigDecimal.valueOf(0.2);
                break;
            default:
                throw new RuntimeException();
        }

        BigDecimal salarioLiquido = salario.multiply(BigDecimal.ONE.subtract(taxa)).setScale(2, RoundingMode.HALF_EVEN);

//        System.out.println("Calculei o seguinte salário líquido: " + salarioLiquido);

        return salarioLiquido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario.setScale(2, RoundingMode.HALF_EVEN);
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
