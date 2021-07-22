package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CozinhaService;

@SpringBootTest
class CadastroCozinhaIT {
	
	@Autowired
	private CozinhaService cozinhaService;

	@Test
	public void deveCadastrarCozinhaComSucesso() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");
		
		novaCozinha = cozinhaService.salvar(novaCozinha);	
		
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}
	
	@Test
	public void naoDeveCadastrarCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);
			
		assertThrows(ConstraintViolationException.class, () -> cozinhaService.salvar(novaCozinha));
	}
	
	@Test
	public void naoDeveExcluirCozinhaEmUso() {
		var idCozinhaEmUso = 1L;
			
		assertThrows(EntidadeEmUsoException.class, () -> cozinhaService.excluir(idCozinhaEmUso));
	}
	
	@Test
	public void naoDeveExcluirCozinhaInexistente() {
		var idCozinhaInexistente = 100L;
			
		assertThrows(CozinhaNaoEncontradaException.class, () -> cozinhaService.excluir(idCozinhaInexistente));
	}

}
