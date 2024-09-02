package com.money.pequenoinvestidor.services;

import com.money.pequenoinvestidor.model.Fii;
import com.money.pequenoinvestidor.model.Ifix;

import java.io.IOException;
import java.util.List;

public interface FiiService {

    String cotacao(String fii) throws IOException;
    String dividendYield(String fii) throws IOException;
    String dividend(String fii) throws IOException;
    String calcularRendiento(String fii, double valorAplicado) throws IOException;
    Fii InformacoesGeraisDoFii(String fii) throws IOException;
    String rendimentoDiarioIFIX() throws IOException;
    List<Ifix> listarFiis() throws IOException;

}
