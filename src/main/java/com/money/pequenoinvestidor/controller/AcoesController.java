package com.money.pequenoinvestidor.controller;

import com.money.pequenoinvestidor.model.Acao;
import com.money.pequenoinvestidor.services.imp.AcoesServiceImp;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/acao")
@Api( value = "Operações sobre ações")
public class AcoesController {

    @Autowired
    AcoesServiceImp acoesService;

    @RequestMapping(method = RequestMethod.GET, value = "/informacaoAcao")
    public ResponseEntity informacoes(@RequestParam(value = "codigo")String codigo) throws IOException {
        Acao acao = acoesService.gerarInformacoesAcao(codigo);
        if (acao != null){
            return new ResponseEntity<>(acao, HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não encontramos a ação que você tentou acessar.");
        }
        //return new ResponseEntity<>("Sistema de ações indisponivel", HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/valuation")
    public ResponseEntity valuationAcao(@RequestParam(value = "codigo")String codigo) throws IOException {
        Acao acao = acoesService.gerarInformacoesAcao(codigo);
        if (acao != null){
            return new ResponseEntity<>(acoesService.montarValuation(acao), HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não encontramos a ação que você tentou acessar.");
        }
        //return new ResponseEntity<>("Sistema de ações indisponivel", HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/bot")
    public ResponseEntity informacoesBot(){
        return ResponseEntity.status(HttpStatus.OK).body(acoesService.dadosBot());
        //return new ResponseEntity<>("Sistema de ações indisponivel", HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/bolsa")
    public ResponseEntity bolsaValores(){
        return ResponseEntity.status(HttpStatus.OK).body("Ok");
        //return new ResponseEntity<>("Sistema de ações indisponivel", HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/bolsa/resultados")
    public ResponseEntity bolsaValoresResultados() throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(acoesService.resultadosBolsa());
        //return new ResponseEntity<>("Sistema de ações indisponivel", HttpStatus.OK);
    }

    @RequestMapping(value ="/dividendos/ano" ,method = RequestMethod.GET)
    public ResponseEntity dividendosPorAcao(@RequestParam(value = "codigo")String codigo,
                                            @RequestParam(value = "valorAplicado") Double valorAplicado) throws IOException {
        Acao acao = acoesService.gerarInformacoesAcao(codigo);
        if (acao != null){
            return new ResponseEntity<>(acoesService.montarDividendosRecebidos(acao, valorAplicado), HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não encontramos a ação que você tentou acessar.");
        }
        //return new ResponseEntity<>("Sistema de ações indisponivel", HttpStatus.OK);
    }
}
