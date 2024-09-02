package com.money.pequenoinvestidor.integração;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.response.Response;

public class FiisTest {
    @Test
	public void devRetornarUrlBaseTest(){
		String responseEsperado = "https://pequenoinvestidoapi.herokuapp.com/swagger-ui.html#";
		String response = given().when()
					.get("https://pequenoinvestidoapi.herokuapp.com/fii/")
				.then()
					.statusCode(200)
					.extract()
					.asString();
		Assert.assertEquals(responseEsperado, response);				
	} 

	@Test
	public void deveRetornarPrecoFii(){
		String codigo = "mxrf11";
		Response response = given()
		 	.when()
				.get("https://pequenoinvestidoapi.herokuapp.com/fii/cotacao?codigo={codigo}",codigo)
			.then()
				.statusCode(200)
				.extract()
				.response();
		Assert.assertTrue(!response.asString().isEmpty());

	}
}
