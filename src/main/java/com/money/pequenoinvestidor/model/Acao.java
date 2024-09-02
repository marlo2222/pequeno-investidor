package com.money.pequenoinvestidor.model;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Acao {
    //informações
    private String codigo;
    private String nomeEmpresa;
    private String valor24H;
    private String variação24H;
    //ultimo mes
    private String variacaoMesAtual;
    private String variacao12Meses;
    private String alta52Semanas;
    private String baixa52Semanas;
    //informações uteis
    private String PL;
    private String ROE;
    private String Div_Pat_Liq;
    //valuation
    private String p_vp;
    private String p_ativos;
    private String VPA;
    private String ev_ebtida;
    private String ROA;
    private String PSR;
    //dividend
    private String dividendYield;
    private String DPA;//dividend por ação
    private String LPA; //lucro por ação
    private String payout;
    //outros indicadores
    private String dividaBruta;
    private String dividaLiquida;
    private String passivo_ativos;
    private String liquidezCorrente;
    private String giroAtivo;
    private String p_EBIT;
    private String margemBruta;
    private String margemLiquida;
    private String margemEBTIDA;
    private String margemEBIT;
    private String ROIC;
    private String GAGR5Years;
    private Date dataAtualizado;
}
