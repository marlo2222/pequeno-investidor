package com.money.pequenoinvestidor;

import com.money.pequenoinvestidor.services.imp.CalculoServiceImp;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class TestCalculoServiceImp {

    @Test
    public void testArredondamentoComCasasDecimais() {
        CalculoServiceImp calculoServiceImp = new CalculoServiceImp();
        String resultado = calculoServiceImp.arredondar(12.345);
        assertEquals("12,35", resultado);
    }

    @Test
    public void testArredondamentoSemCasasDecimais() {
        CalculoServiceImp calculoServiceImp = new CalculoServiceImp();
        String resultado = calculoServiceImp.arredondar(12);
        assertEquals("12,00", resultado);
    }

    @Test
    public void testArredondamentoMeio() {
        CalculoServiceImp calculoServiceImp = new CalculoServiceImp();
        String resultado = calculoServiceImp.arredondar(12.5);
        assertEquals("12,50", resultado);
    }

    @Test
    public void testConverteValorFiiComPonto() {
        CalculoServiceImp calculoServiceImp = new CalculoServiceImp();
        double valorConvertido = calculoServiceImp.converteValorFii("R$ 100.50");
        assertEquals(100.50, valorConvertido, 0.01);
    }

    @Test
    public void testConverteValorFiiComVirgula() {
        CalculoServiceImp calculoServiceImp = new CalculoServiceImp();
        double valorConvertido = calculoServiceImp.converteValorFii("R$ 50,75");
        assertEquals(50.75, valorConvertido, 0.01);
    }

    @Test
    public void testConverteValorFiiSemPrefixo() {
        CalculoServiceImp calculoServiceImp = new CalculoServiceImp();
        double valorConvertido = calculoServiceImp.converteValorFii("75.30");
        assertEquals(75.30, valorConvertido, 0.01);
    }

    @Test
    public void testCalculoRendimentoValoresInteirosPositivosRetornaString() {
        CalculoServiceImp calculoServiceImp = new CalculoServiceImp();
        String resultado = calculoServiceImp.calculoRedimento(1, 2, 3);
        assertEquals("6,00", resultado);
    }

    @Test
    public void testCalculoRendimentoValoresDoublePositivosRetornaString() {
        CalculoServiceImp calculoServiceImp = new CalculoServiceImp();
        String resultado = calculoServiceImp.calculoRedimento(1.5, 2.5, 3.5);
        assertEquals("5,83", resultado);
    }

    @Test
    @Disabled
    public void testCalculoRendimentoParametroZeroRetornaString() {
        CalculoServiceImp calculoServiceImp = new CalculoServiceImp();
        String resultado = calculoServiceImp.calculoRedimento(1, 0, 3);
        assertEquals("0,00", resultado);
    }

    @Test
    public void valorDividend12MesesTest() {
        Double resultadoEsperado = 16.00;
        Double quantidade = 2.00;
        String valor = "8.00";

        CalculoServiceImp calculoServiceImp = new CalculoServiceImp();
        Double resultadoRecebido = calculoServiceImp.valorDividend12Meses(quantidade, valor);
        assertEquals(resultadoEsperado, resultadoRecebido);
    }

    @Test
    public void valorDividend12MesesComValorNegativoTest() {
        Double resultadoEsperado = -16.00;
        Double quantidade = 2.00;
        String valor = "-8.00";

        CalculoServiceImp calculoServiceImp = new CalculoServiceImp();
        Double resultadoRecebido = calculoServiceImp.valorDividend12Meses(quantidade, valor);
        assertEquals(resultadoEsperado, resultadoRecebido);
    }

    @Test
    public void valorDividend12MesesComQuantidadeNegativoTest() {
        Double resultadoEsperado = -16.00;
        Double quantidade = -2.00;
        String valor = "8.00";

        CalculoServiceImp calculoServiceImp = new CalculoServiceImp();
        Double resultadoRecebido = calculoServiceImp.valorDividend12Meses(quantidade, valor);
        assertEquals(resultadoEsperado, resultadoRecebido);
    }

    @Test
    public void qtdAcoesValorTest() {
        Double resultadoEsperado = 2.0;
        CalculoServiceImp calculoServiceImp = new CalculoServiceImp();
        Double result = calculoServiceImp.qtdAcoesValor("32.17", 64.34);
        assertEquals(resultadoEsperado, result);
    }
}
