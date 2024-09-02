package com.money.pequenoinvestidor.controller;

import com.money.pequenoinvestidor.model.Fii;
import com.money.pequenoinvestidor.model.Ifix;
import com.money.pequenoinvestidor.services.imp.FiiServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/fii")
@Api(value = "Retorna iinformações sobre FIIs")
public class FiiController {

    @Autowired
    FiiServiceImp financeService;

    @GetMapping("/")
    public ResponseEntity<?> home(){
        return new ResponseEntity<>("https://pequenoinvestidoapi.herokuapp.com/swagger-ui.html#", HttpStatus.OK);
    }

    @GetMapping("/cotacao")
    @ApiOperation(value = "Retorna a cotação de um FII.")
    public ResponseEntity<?> cotacao(@RequestParam(value = "codigo")String codigo) throws IOException {
        final String response = financeService.cotacao(codigo);
        if (response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Não foi possivel busca pelo codigo do fii ["+codigo+"]", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/dividend")
    @ApiOperation(value = "Retorna o valor do ultimo divendendo pago de um FII.")
    public ResponseEntity<?> dividend(@RequestParam(value = "codigo")String codigo) throws IOException {
        final String response = financeService.dividend(codigo);
        if(response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Não foi possivel busca pelo codigo do fii ["+codigo+"]", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/dy")
    @ApiOperation(value = "Retorna o dividend yield de um FII.")
    public ResponseEntity<?> dividendYield(@RequestParam(value = "codigo")String codigo) throws IOException {
        final String response = financeService.dividendYield(codigo);
        if(response !=  null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Não foi possivel busca pelo codigo do fii ["+codigo+"]", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/rendimento")
    @ApiOperation(value = "Retorna o rendimento com base no (valor aplicado*ultimo rendimento) de um FII ")
    public ResponseEntity<?> rendimento(@RequestParam(value = "codigo") String codigo,
                             @RequestParam(value = "valor", defaultValue = "0.00") Double valor) throws IOException {
        final String response = financeService.calcularRendiento(codigo, valor);
        if(response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Não foi possivel busca pelo codigo do fii ["+codigo+"]", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/informacoes")
    @ApiOperation(value = "Retorna uma serie de informações de um FII.")
    public ResponseEntity<?> informacoesFii(@RequestParam(value = "codigo")String codigo) throws IOException {
        final Fii response = financeService.InformacoesGeraisDoFii(codigo);
        if(response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Não foi possivel busca pelo codigo do fii ["+codigo+"]", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/ifix")
    @ApiOperation(value = "Retorna o rendimento diario do indice IFIX. \n Maiores altas e maiores baixas..")
    public ResponseEntity<?> ifix() throws IOException {
        final String response = financeService.rendimentoDiarioIFIX();
        if(response != null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Não foi possivel busca rendimento diario", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listAll")
    @ApiOperation(value = "Retorna todos os fundos negociados na bolsa.")
    public ResponseEntity<?> listAllFiis() throws IOException {
        final List<Ifix> response = financeService.listarFiis();
        if(response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Não foi possivel buscar pla lista de FIIs", HttpStatus.BAD_REQUEST);
        }
    }
}
