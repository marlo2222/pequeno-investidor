package com.money.pequenoinvestidor.util;

import org.springframework.stereotype.Component;

@Component
public class ElementsAcaoUtils {
    //informações
    public String codigo = "flex-column flex-md-row ticker-name";//class
    public String nomeEmpresa = "singleInfoSubtitle"; //class
    public String valor24H = "ticker-value"; //class
    public String valor24HId = "cotacao"; //class
    public String variacao24H = "variationValue"; //class
    public String variacao24HId = "variacao"; //class

    //ultimo mes
    public String variacaoMesAtual = "styles__TickerInfo-sc-ybq6a2-1"; //class
    public String variacao12Meses = "styles__TickerInfo-sc-ybq6a2-1"; //class
    public String alta52Semanas = "styles__TickerInfo-sc-ybq6a2-1"; //class
    public String baixa52Semanas = "styles__TickerInfo-sc-ybq6a2-1"; //class
    //informações uteis
    public String indicadores = "container-mobile";
    private String ROE;
    private String Div_Pat_Liq;
    //valuation
    public String valuation = "indicador-valuation";
    //dividend
    public String dividendYield = "singleDividendosRodapeSessao";
    private String DPA;//dividend por ação
    private String LPA; //lucro por ação
    private String payout;

    //outros valores
    public String outrosValore = "outrosValor";
}
