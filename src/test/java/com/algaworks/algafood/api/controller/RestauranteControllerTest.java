package com.algaworks.algafood.api.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.algaworks.algafood.builder.RestauranteBuilder;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RestauranteControllerTest {
	
	private static final String VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE = "Violação de regra de negócio";

    private static final String DADOS_INVALIDOS_PROBLEM_TITLE = "Dados inválidos";
	
	private static final long ID_RESTAURANTE_INEXISTENTE = 100L;

	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	private int qtdeRestaurantes;

	private Restaurante restauranteComidaMineira;
	
	@BeforeEach
	public void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/restaurantes";
		
		databaseCleaner.clearTables();
		prepararDados();
	}
	
	@Test
	public void deveRetornarStatusSucessoQuandoBuscarRestaurantes() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveVerificarQtdeQuandoBuscarRestaurantes() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(qtdeRestaurantes));
	}
	
	@Test
	public void deveRetornarStatusCriacaoQuandoCadastrarUmRestaurante() {
		Restaurante restaurante = RestauranteBuilder.builder().build().buildRestaurante();
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(restaurante)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarStatusBadRequestQuandoCadastrarRestauranteComIdCozinhaInexistente() {
		Restaurante restaurante = RestauranteBuilder.builder().build().buildRestaurante();
		restaurante.getCozinha().setId(100L);
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(restaurante)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE));
	}
	
	@Test
	public void deveRetornarStatusBadRequestQuandoCadastrarRestauranteSemTaxaFrete() {
		Restaurante restaurante = RestauranteBuilder.builder().build().buildRestaurante();
		restaurante.setTaxaFrete(null);
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(restaurante)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));;
	}
	
	@Test
	public void deveRetornarStatusBadRequestQuandoCadastrarRestauranteSemCozinha() {
		Restaurante restaurante = RestauranteBuilder.builder().build().buildRestaurante();
		restaurante.setCozinha(null);
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(restaurante)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));;
	}
	
	
	@Test
	public void deveRetornarStatusSucessoQuandoBuscarDeterminadoRestaurante() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/{id}", restauranteComidaMineira.getId())
		.then()
			.body("nome", Matchers.equalTo(restauranteComidaMineira.getNome()));
	}
	
	@Test
	public void deveRetornarStatusNaoEncontradoQuandoBuscarRestauranteInexistente() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/{id}", ID_RESTAURANTE_INEXISTENTE)
		.then()
		.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private void prepararDados() {
		
		Cozinha cozinhaArgentina = new Cozinha();
		cozinhaArgentina.setNome("Argentina");
		cozinhaRepository.save(cozinhaArgentina);
		
		Cozinha cozinhaBrasileira = new Cozinha();
		cozinhaBrasileira.setNome("Brasileira");
		cozinhaRepository.save(cozinhaBrasileira);
		
		restauranteComidaMineira = new Restaurante();
		restauranteComidaMineira.setNome("Tempero Brasileiro");
		restauranteComidaMineira.setTaxaFrete(new BigDecimal(10));
		restauranteComidaMineira.setCozinha(cozinhaBrasileira);
		restauranteRepository.save(restauranteComidaMineira);
		
		Restaurante restauranteArgentino = new Restaurante();
		restauranteArgentino.setNome("Cabanhas");
		restauranteArgentino.setTaxaFrete(new BigDecimal(15));
		restauranteArgentino.setCozinha(cozinhaArgentina);
		restauranteRepository.save(restauranteArgentino);
		
		qtdeRestaurantes = (int) restauranteRepository.count();
	}

}
