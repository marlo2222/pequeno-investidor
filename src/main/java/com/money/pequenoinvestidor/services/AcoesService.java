package com.money.pequenoinvestidor.services;

import com.money.pequenoinvestidor.model.Acao;
import java.io.IOException;

public interface AcoesService {
    Acao gerarInformacoesAcao(String codigo) throws IOException;
    String montarValuation(Acao acao);
    String dadosBot();
    String resultadosBolsa() throws IOException;
    String montarDividendosRecebidos(Acao acao, Double valorAplicado);
}
