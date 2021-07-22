package com.algaworks.algafood.builder;

import com.algaworks.algafood.domain.model.Cozinha;

import lombok.Builder;

@Builder
public class CozinhaBuilder {
	
	@Builder.Default
	private String nome = "Chinesa";
	
	public Cozinha buildCozinha() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(nome);
		
		return novaCozinha;
	}

}
