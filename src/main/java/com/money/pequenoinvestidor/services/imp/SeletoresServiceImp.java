package com.money.pequenoinvestidor.services.imp;

import com.money.pequenoinvestidor.model.Fii;
import com.money.pequenoinvestidor.model.Ifix;
import com.money.pequenoinvestidor.services.SeletoresService;
import com.vdurmont.emoji.EmojiParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
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
        return doc.getElementById("stock-price").getElementsByClass("price").html();
    }

    public String buscarFiiValorizacaoDia(Document doc){
        return doc.getElementById("stock-price").getElementsByClass("percentage positive").html();
    }
    public String buscarLiquidezDiaria(Document doc){
        return doc.getElementById("main-indicators-carousel").getElementsByClass("indicator-value").get(0).html();
    }
    public String buscarDividend(Document doc){
        return doc.getElementById("main-indicators-carousel").getElementsByClass("indicator-value").get(1).html();
    }
    public String buscarDivideldYield( Document doc){
        return doc.getElementById("main-indicators-carousel").getElementsByClass("indicator-value").get(2).html();
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
        final String valorizacao = doc.getElementById("stock-price").getElementsByClass("percentage positive").html();
        return new Fii(fii, fii, 
                doc.getElementById("stock-price").getElementsByClass("price").html(),
                doc.getElementById("stock-price").getElementsByClass(valorizacao.isEmpty() ? "percentage negative" : "percentage positive").html(),
                doc.getElementById("main-indicators-carousel").getElementsByClass("indicator-value").get(0).html(),
                doc.getElementById("main-indicators-carousel").getElementsByClass("indicator-value").get(1).html(),
                doc.getElementById("main-indicators-carousel").getElementsByClass("indicator-value").get(2).html(),
                doc.getElementById("main-indicators-carousel").getElementsByClass("indicator-value").get(3).html(),
                doc.getElementById("main-indicators-carousel").getElementsByClass("indicator-value").get(4).html(),
                doc.getElementById("main-indicators-carousel").getElementsByClass("indicator-value").get(5).html(),
                doc.getElementById("main-indicators-carousel").getElementsByClass("indicator-value").get(6).html(),
                LocalDate.now().toString());
    }


    public List<Ifix> ListAllIfix(Document doc){
       Elements lista = doc.getElementById("search-menu-select").getElementsByClass("form-control fe-input").select("option");
       for (Element l: lista) {
           if (l.hasText()){
               ifixList.add(new Ifix(l.val(), l.text()));
            }
        }
       return ifixList;
    }
}
