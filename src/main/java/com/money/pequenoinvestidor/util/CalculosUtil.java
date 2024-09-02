package com.money.pequenoinvestidor.util;

import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@Component
public class CalculosUtil {

    public double converteValorFii(String valor){ 
        valor = valor.replace("R$ ", "");
        return Double.valueOf(valor.replace(",", "."));
    }
    public double converteRedimentoFii(String valor){
        valor = valor.replace("R$ ", "");
        return Double.valueOf(valor.replace(",", "."));
    }
    public String calculoRedimento(double valorFii, double ultimoRendimento, double valorAplicado){
        return arredondar(((double) valorAplicado/valorFii) * ultimoRendimento);
    }

    public  String arredondar(double valor) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(valor);
    }

    public Double valorDividend12Meses(Double quantidade, String valor){
        return quantidade * Double.parseDouble(formatarValor(valor));
    }
    public Double qtdAcoesValor(String valor, double valorAplicado){
        return (valorAplicado/Double.parseDouble(formatarValor(valor)));
    }
    private String formatarValor(String valor){
        if(valor != null){
            return valor.replace(",", ".");
        }else{
            return "0.00";
        }
    }
}
