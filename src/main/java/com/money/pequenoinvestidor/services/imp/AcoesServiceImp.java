package com.money.pequenoinvestidor.services.imp;
import com.money.pequenoinvestidor.model.Acao;
import com.money.pequenoinvestidor.services.AcoesService;
import com.money.pequenoinvestidor.util.ElementsAcaoUtils;
import com.vdurmont.emoji.EmojiParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class AcoesServiceImp implements AcoesService {

    @Autowired
    SeletoresServiceImp seletores;
    @Autowired
    ElementsAcaoUtils elementsAcaoUtils;
    @Autowired
    CalculoServiceImp calculos;

    private static final Logger logger = LoggerFactory.getLogger(AcoesServiceImp.class);
    DateFormat dfm = DateFormat.getDateInstance(DateFormat.MEDIUM);
    DateFormat dffFull = DateFormat.getDateInstance(DateFormat.FULL);

    private static Map<String, Acao> listaAcoes = new HashMap<>();

    public Acao gerarInformacoesAcao(String codigo) throws IOException {
        Document doc = seletores.buscarInformacoes(URL(codigo));
        logger.info("Realizada busca da acao: " + codigo.toUpperCase());
        Acao acao = new Acao();
        if(!doc.getElementsByClass("notfoundContainer").hasText()){
            logger.info("Codigo da acao encontrado: " + codigo.toUpperCase());
            Elements variacaoMes = seletores.listByClass(doc, elementsAcaoUtils.variacaoMesAtual);
            Element indicadores =  seletores.listByClass(doc, elementsAcaoUtils.indicadores).get(0);

            Elements elements2 = seletores.listByClass(doc, elementsAcaoUtils.dividendYield);
            Elements outrosValores = seletores.listByClass(doc, elementsAcaoUtils.outrosValore);
            Elements valoresValuation = seletores.listByClass(doc, elementsAcaoUtils.valuation);
            //informações
            acao.setCodigo(codigo.toUpperCase());
            acao.setNomeEmpresa(doc.getElementsByClass(elementsAcaoUtils.codigo).select("span").first().text());
            acao.setValor24H("R$" + doc.getElementsByClass(elementsAcaoUtils.valor24H).select("div").first().select("b").first().text());
            acao.setVariação24H(replaceEspaco(doc.getElementsByClass(elementsAcaoUtils.variacao24H).first().text()));
            //varicao mes
            acao.setVariacaoMesAtual(replaceEspaco(variacaoMes.get(0).select("span").first().text()));
            acao.setVariacao12Meses(replaceEspaco(variacaoMes.get(1).select("span").first().text()));
            acao.setBaixa52Semanas(replaceEspaco(variacaoMes.get(2).select("span").first().text()));
            acao.setAlta52Semanas(replaceEspaco(variacaoMes.get(3).select("span").first().text()));
            //indicadores
            acao.setDividaBruta(indicadores.child(0).child(0).select("span").first().text());
            acao.setDividaLiquida(indicadores.child(0).child(1).select("span").first().text());
            acao.setPassivo_ativos(indicadores.child(0).child(2).select("span").first().text());
            acao.setLiquidezCorrente(indicadores.child(0).child(3).select("span").first().text());
            acao.setGiroAtivo(indicadores.child(0).child(4).select("span").first().text());
            acao.setP_EBIT(indicadores.child(0).child(5).select("span").first().text());
            acao.setMargemBruta(indicadores.child(1).child(0).select("span").first().text());
            acao.setMargemLiquida(indicadores.child(1).child(1).select("span").first().text());
            acao.setMargemEBTIDA(indicadores.child(1).child(2).select("span").first().text());
            acao.setMargemEBIT(indicadores.child(1).child(3).select("span").first().text());
            acao.setROIC(indicadores.child(1).child(4).select("span").first().text());
            acao.setGAGR5Years(indicadores.child(1).child(5).select("span").first().text());
            acao.setDividendYield(doc.getElementById("dividendos").child(1).child(2).child(2).child(1).select("span").first().text());

            System.out.println(doc.getElementsByClass("hLGwpV0").first().child(1).select("span").first().text());

            /* acao.setP_vp(valoresValuation.get(0).html());
            acao.setP_ativos(valoresValuation.get(1).html());
            acao.setVPA(valoresValuation.get(2).html());
            acao.setEv_ebtida(valoresValuation.get(3).html());
            acao.setROA(valoresValuation.get(4).html());
            acao.setPSR(valoresValuation.get(5).html());
            acao.setDataAtualizado(new Date());*/
            // acao.setPL(indicadores.get(0).select("span").last().text());
            // acao.setROE(indicadores.get(1).select("span").last().text());
            // acao.setDiv_Pat_Liq(indicadores.get(2).select("span").last().text());


            // acao.setDPA(elements2.get(1).child(0).child(1).html());
            // acao.setLPA(elements2.get(2).child(0).child(1).html());
            //acao.setPayout(elements2.get(3).child(0).html());
            listaAcoes.put(codigo.toUpperCase(), acao);
            logger.info("Ação adicionada a memoria: " + codigo.toUpperCase());
            return acao;
        }else{
            logger.info("Codigo da acao não encontrado: " + codigo.toUpperCase());
            return null;
        }
    }
    private synchronized boolean acaoJaBuscada(String codigo){
        if (!listaAcoes.isEmpty()){
            logger.info("Lista de ações não está vazia");
            if (listaAcoes.containsKey(codigo)){
                logger.info("Lista de ações contem: "+ codigo);
                return true;
            }else{
                logger.info("Lista de ações não contem: "+ codigo);
                return false;
            }
        }
        logger.info("Lista de ações está vazia");
        return false;
    }

    public String montarValuation(Acao acao){
        String texto = "";
        texto += ":bank:" + acao.getNomeEmpresa() + "\n";
        texto += ":moneybag:Valuation de "+acao.getCodigo() +"\n\n";
        texto += ":mag_right:P/VP: "+ acao.getP_vp() +".\n";
        texto += ":mag_right:P/Ativos: "+ acao.getP_ativos()+".\n";
        texto += ":mag_right:VPA: "+ acao.getVPA()+".\n";
        texto += ":mag_right:EV/Ebtida: "+ acao.getEv_ebtida()+".\n";
        texto += ":mag_right:ROA: "+ acao.getROA()+".\n";
        texto += ":mag_right:PSR: "+ acao.getPSR()+".\n\n";
        texto += ":chart_with_upwards_trend:Você gostaria de construir um patrimônio sólido e viver de dividendos?:rocket:\n";
        texto += "Vem saber como :point_right: \n\n";
        return EmojiParser.parseToUnicode(texto);
    }

    public String dadosBot(){
        String texto = "";
        texto += ":construction_worker:Comandos disponiveis do riquinho, seu bot financeiro.\n";
        texto += ":point_right:valuation: Retorna informações do valuation.\n";
        texto += ":point_right:dy: Retorna infomações do dividend yield.\n";
        texto += ":point_right:infor: Retorna as principais informações de uma ação.\n";
        texto += ":point_right:help: para outros comandos!:point_right:\n\n";
        texto += ":pray:Nos acompanhe nas redes sociais:\n";
        return EmojiParser.parseToUnicode(texto);
    }
    
    public String resultadosBolsa() throws IOException {
        logger.info("Buscando resultados bol");
        Document doc = seletores.buscarInformacoes(URLInformacoesBolsa());
        Elements elements = seletores.listByClass(doc, "w-100 w-sm-50 w-xl-25 mt-4 mt-xl-0");
         Elements acoes = elements.get(0).getElementsByClass("info w-100");

         for (Element acao : acoes) {
            System.out.println(
                    acao.select("h4").text()+" "+
                    acao.select("span").get(2).text()+" "+
                    acao.select("span").get(0).text().replace("arrow_upward ", "+")

            );
        }   
            
      


        /*Elements elements = seletores.buscarListaElementosPorClass(doc, "w-100 w-sm-50 w-xl-25 mt-4 mt-xl-0");
        gerarAltasBolsa(elements.get(0).child(1));
        System.out.println( elements.get(0).child(1).child(1).html().toString());
        return elements.get(0).child(1).child(1).select("info w-100").toString();/*
         */
        return "a";

    }

    private void gerarAltasBolsa(Element child) {
        System.out.println(child.child(0).child(0).child(0).html().toString());
    }
    private String URL(String codigo){
        return "https://www.suno.com.br/acoes/"+codigo.toLowerCase();
    }
    private String URLInformacoesBolsa(){
        return "https://statusinvest.com.br/";
    }
    private String URLSuno(){
        return "https://www.suno.com.br/";
    }

    public String montarDividendosRecebidos(Acao acao, Double valorAplicado) {
        Double qtd = calculos.qtdAcoesValor(acao.getValor24H(), valorAplicado);

        Double valorRecebido = calculos.valorDividend12Meses(qtd, acao.getDPA());
        String body = "%s \n";//nome empresa
        body += "%.2f reais investidos em %s:moneybag: \n";
        body += ":red_circle:resultado dos ultimos 12 meses::red_circle: \n"; //valor e codigo acao
        body += ":heavy_check_mark:%.0f ações de %s. \n"; //qtd de ações que o valor aplicado redeu
        body += ":heavy_check_mark:%s de valorizacao por ação. \n"; //valorização 12 meses
        body += ":heavy_check_mark:%.2f de reais recebidos por ação.\n";
      //  body += ":warning::warning:Importante: Resultado dos ultimos 12 meses.:warning::warning:";

        return EmojiParser.parseToUnicode(String.format(body,
                acao.getNomeEmpresa(),
                valorAplicado, acao.getCodigo(),
                qtd,
                acao.getCodigo(),
                acao.getVariacao12Meses(),
                valorRecebido));
    }

    private String replaceEspaco(String texto){
        return texto.replace(" ", "");
    }

}
