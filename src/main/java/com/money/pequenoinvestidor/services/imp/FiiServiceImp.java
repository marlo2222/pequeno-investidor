package com.money.pequenoinvestidor.services.imp;

import com.money.pequenoinvestidor.model.Fii;
import com.money.pequenoinvestidor.model.Ifix;
import com.money.pequenoinvestidor.services.FiiService;

import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FiiServiceImp implements FiiService {

    @Autowired
    SeletoresServiceImp seletores;
    @Autowired
    CalculoServiceImp calculos;
    private static final Logger logger = LoggerFactory.getLogger(FiiServiceImp.class);

    public List<Ifix> listarFiis() throws IOException {
        if(SeletoresServiceImp.ifixList.isEmpty()){
            try{
                logger.info("FIIs not yet listed.");
                Document doc = seletores.buscarInformacoes(URLAllFiis());
                return seletores.ListAllIfix(doc);
            }catch (HttpStatusException e){
                logger.warn("Não foi possivel buscar pla lista de FIIs", e.getMessage());
                return null;
            }
        }else{
            logger.info("FIIs have already been listed.");
            return SeletoresServiceImp.ifixList;
        }
    }

    //para consulta da cotação do fii
    public String cotacao(String fii) throws IOException {
        try{
            Document doc = seletores.buscarInformacoes(URL(fii));
            return seletores.buscarFiiValor(doc);
        }catch (HttpStatusException e){
            logger.warn("Não foi possivel busca pelo codigo do fii ["+fii+"]", e.getMessage());
            return null;
        }
    }

    //para consultas do dy do fii
    public String dividendYield(String fii) throws IOException {
        try{
            Document doc = seletores.buscarInformacoes(URL(fii));
            return seletores.buscarDivideldYield(doc);
        }catch (HttpStatusException e){
            logger.warn("Não foi possivel busca pelo codigo do fii ["+fii+"]", e.getMessage());
            return null;
        }
    }

    //para consultas do dividendo do fii
    public String dividend(String fii) throws IOException {
        try {
            Document doc = seletores.buscarInformacoes(URL(fii));
            return seletores.buscarDividend(doc);
        }catch (HttpStatusException e){
            logger.warn("Não foi possivel busca pelo codigo do fii ["+fii+"]", e.getMessage());
            return null;
        }
    }

    //calculo de rendimento
    public String calcularRendiento(String fii, double valorAplicado) throws IOException {
        try{
            Document doc = seletores.buscarInformacoes(URL(fii));
            double valorFii =  calculos.converteValorFii(seletores.buscarFiiValor(doc));
            double ultimoRendimento = calculos.converteRedimentoFii(seletores.buscarDividend(doc));
            return calculos.calculoRedimento(valorFii, ultimoRendimento, valorAplicado);
        }catch(HttpStatusException e){
            logger.warn("Não foi possivel busca pelo codigo do fii ["+fii+"]", e.getMessage());
            return null;
        }
    }
    //informações do fii
    public Fii InformacoesGeraisDoFii(String fii) throws IOException {
        try {
            return seletores.informacoesFiiGeral(URL(fii), fii);
        }catch (HttpStatusException e){
            logger.warn("Não foi possivel busca pelo codigo do fii ["+fii+"]", e.getMessage());
            return null;
        }
    }
    //rendimento diario do indice ifix
    public String rendimentoDiarioIFIX() throws IOException {
        try{
            Document doc = seletores.buscarInformacoes(URLDadosIfix());
            return seletores.informacoesIfix(doc);
        }catch (HttpStatusException e){
            logger.warn("Não foi possivel busca rendimento diario", e.getMessage());
            return null;
        }
    }
    private String URL(String fii){
        return "https://www.fundsexplorer.com.br/funds/"+fii.toLowerCase();
    }
    private String URLDadosIfix(){
        return "https://www.fundsexplorer.com.br/";
    }
    private String URLAllFiis(){
        return "https://www.fundsexplorer.com.br/funds";
    }
    private String FiisStatusInvest(){
        return "https://statusinvest.com.br/fundos-imobiliarios";
    }
}
