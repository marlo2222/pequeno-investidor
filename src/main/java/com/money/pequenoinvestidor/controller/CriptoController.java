package com.money.pequenoinvestidor.controller;

import com.money.pequenoinvestidor.services.CriptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coin")
public class CriptoController {

    @Autowired
    CriptoService criptoService;

    @GetMapping("/")
    public String home(){
        return "Teste de Moedas";
    }

    @GetMapping("/moeda")
    public String tickes(@RequestParam(value = "codigo") String codigo){
       return  criptoService.moeda(codigo);
    }

    @GetMapping("/currentValue")
    public ResponseEntity<?> currentValue(){
        return  new ResponseEntity<>(criptoService.currentValue(), HttpStatus.OK);
    }

}
