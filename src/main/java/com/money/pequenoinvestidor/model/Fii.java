package com.money.pequenoinvestidor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;


@Data
@Log
@AllArgsConstructor
@NoArgsConstructor
public class Fii {

    private String codigo;
    private String nomeFii;
    private String cotacao;
    private String valorizacaoDia;
    private String liquidezDiaria;
    private String ultimoRedimento;
    private String dividendYield;
    private String patrimonioLiquido;
    private String valorPatrimonial;
    private String RentabilidadeMes;
    private String pvp;
    private String ultimaAtualização;
}
