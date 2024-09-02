package com.money.pequenoinvestidor.services;

import java.io.IOException;

import javax.lang.model.util.Elements;

import org.jsoup.nodes.Document;

import com.money.pequenoinvestidor.model.Fii;

public interface SeletoresService {
    String buscarElementoPorClass(Document doc, String elemento);
    org.jsoup.select.Elements listByClass(Document doc, String elemento);
    String buscarFiiValor(Document doc);
    String buscarFiiValorizacaoDia(Document doc);
    String buscarLiquidezDiaria(Document doc);
    String buscarDividend(Document doc);
    String buscarDivideldYield( Document doc);
    String buscarPatrimonioLiquido( Document doc);
    String buscarValorPatrimonial( Document doc);
    String buscarRentabilidadeMes( Document doc);
    String buscarPVP( Document doc);
    Document buscarInformacoes(String fiiUrl) throws IOException;
    Fii informacoesFiiGeral(String URL, String fii) throws IOException;
    void fiiJaConsultado(String URL, String fii) throws IOException;
    Fii criarModelo(String URL, String fii) throws IOException;
    String informacoesIfix(Document doc);
}
