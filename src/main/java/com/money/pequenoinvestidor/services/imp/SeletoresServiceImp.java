package com.money.pequenoinvestidor.services.imp;

import com.money.pequenoinvestidor.model.Fii;
import com.money.pequenoinvestidor.model.Ifix;
import com.money.pequenoinvestidor.services.SeletoresService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
public class SeletoresServiceImp implements SeletoresService {

    private HashMap<String, Fii> fiiModeList = new HashMap<>();
    public static List<Ifix> ifixList = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(SeletoresServiceImp.class);

    public String buscarElementoPorClass(Document doc, String elemento){
        return doc.getElementsByClass(elemento).html();
    }
    public Elements listByClass(Document doc, String elemento){
        return doc.getElementsByClass(elemento);
    }
    public String buscarFiiValor(Document doc){
        return doc.select("div.headerTicker__content__price p").first().text();
    }

    public String buscarFiiValorizacaoDia(Document doc){
        return doc.select("div.headerTicker__content__price span").first().text();
    }
    public String buscarLiquidezDiaria(Document doc){
        return doc.getElementById("main-indicators-carousel").getElementsByClass("indicator-value").get(0).html();
    }
    public String buscarDividend(Document doc){
        return doc.select("div.indicators__box p b").get(1).text();
    }
    public String buscarDivideldYield( Document doc){
        return doc.select("div.indicators__box p b").get(2).text();
    }
    public String buscarPatrimonioLiquido( Document doc){
        return doc.getElementById("main-indicators-carousel").getElementsByClass("indicator-value").get(3).html();
    }
    public String buscarValorPatrimonial( Document doc){
        return doc.getElementById("main-indicators-carousel").getElementsByClass("indicator-value").get(4).html();
    }
    public String buscarRentabilidadeMes( Document doc){
        return doc.getElementById("main-indicators-carousel").getElementsByClass("indicator-value").get(5).html();
    }
    public String buscarPVP( Document doc){
        return doc.getElementById("main-indicators-carousel").getElementsByClass("indicator-value").get(6).html();
    }
    public Document buscarInformacoes(String fiiUrl) throws IOException {
        logger.info("BUSCANDO INFORMAÇÔES ->"+ fiiUrl);
        return Jsoup.connect(fiiUrl).get();
    }
    public Fii informacoesFiiGeral(String URL, String fii) throws IOException {
        fiiJaConsultado(URL, fii);
        return fiiModeList.get(fii);
    }

    // se ja tiver cadastrado( verificação da data e hora, e se é final de semana)
    // se não estiver cadastrado, adiconar esse cara.
    public void fiiJaConsultado(String URL, String fii) throws IOException {
        if (!fiiModeList.containsKey(fii)){
            Fii fiiModel = criarModelo(URL, fii);
            fiiModeList.put(fii, fiiModel);
            logger.info("The following is not cached -> ["+fii+"]");
        }else{
            logger.info("The following index is cached -> ["+fii+"]");
        }
    }
    public Fii criarModelo(String URL, String fii) throws IOException {
        Document doc = buscarInformacoes(URL);
        //final String valorizacao = doc.getElementById("stock-price").getElementsByClass("percentage positive").html();
        return new Fii(fii, fii, 
                doc.select("div.headerTicker__content__price p").first().text(),
                doc.select("div.headerTicker__content__price span").first().text(),
                doc.select("div.indicators__box p b").get(0).text(),
                doc.select("div.indicators__box p b").get(1).text(),
                doc.select("div.indicators__box p b").get(2).text(),
                doc.select("div.indicators__box p b").get(3).text(),
                doc.select("div.indicators__box p b").get(4).text(),
                doc.select("div.indicators__box p b").get(5).text(),
                doc.select("div.indicators__box p b").get(6).text(),
                LocalDate.now().toString());
    }

    @Override
    public String informacoesIfix(Document doc) {
        return null;
    }


    public List<Ifix> ListAllIfix(Document doc){
       Elements lista = doc.getElementsByClass("link-tickers-container");
       for (Element element: lista) {
           if (element.hasText()){
               ifixList.add(new Ifix(element.select("div.tickerBox__desc").first().text(), element.select("div[data-element=ticker-box-title]").first().text()));
            }
        }
       return ifixList;
    }
}
