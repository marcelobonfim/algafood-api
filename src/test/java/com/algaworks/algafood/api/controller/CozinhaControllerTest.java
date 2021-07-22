package com.algaworks.algafood.api.controller;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.algaworks.algafood.builder.CozinhaBuilder;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CozinhaControllerTest {
	
	private static final long ID_COZINHA_INEXISTENTE = 100L;

	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	private int qtdeCozinhas;

	private Cozinha cozinhaTailandesa;
	
	@BeforeEach
	public void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		databaseCleaner.clearTables();
		prepararDados();
	}
	
	@Test
	public void deveRetornarStatusSucessoQuandoBuscarCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveVerificarQtdeQuandoBuscarCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(qtdeCozinhas));
	}
	
	@Test
	public void deveRetornarStatusCriacaoQuandoCadastrarUmaCozinha() {
		Cozinha cozinha = CozinhaBuilder.builder().build().buildCozinha();
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(cozinha)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarStatusSucessoQuandoBuscarDeterminadaCozinha() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/{id}", cozinhaTailandesa.getId())
		.then()
			.body("nome", Matchers.equalTo(cozinhaTailandesa.getNome()));
	}
	
	@Test
	public void deveRetornarStatusNaoEncontradoQuandoBuscarCozinhaInexistente() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/{id}", ID_COZINHA_INEXISTENTE)
		.then()
		.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private void prepararDados() {
		cozinhaTailandesa = new Cozinha();
		cozinhaTailandesa.setNome("Tailandesa");
		cozinhaRepository.save(cozinhaTailandesa);
		
		Cozinha cozinhaIndiana = new Cozinha();
		cozinhaIndiana.setNome("Indiana");
		cozinhaRepository.save(cozinhaIndiana);
		
		Cozinha cozinhaArgentina = new Cozinha();
		cozinhaArgentina.setNome("Argentina");
		cozinhaRepository.save(cozinhaArgentina);
		
		Cozinha cozinhaBrasileira = new Cozinha();
		cozinhaBrasileira.setNome("Brasileira");
		cozinhaRepository.save(cozinhaBrasileira);
		
		qtdeCozinhas = (int) cozinhaRepository.count();
	}

}
