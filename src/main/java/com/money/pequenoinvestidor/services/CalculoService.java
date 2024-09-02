package com.money.pequenoinvestidor.services;

public interface CalculoService {

    double converteValorFii(String valor);
    double converteRedimentoFii(String valor);
    String calculoRedimento(double a, double b, double c);
    String arredondar(double valor);
    Double valorDividend12Meses(Double quantidade, String valor);
    Double qtdAcoesValor(String valor, double valorAplicado);

}
