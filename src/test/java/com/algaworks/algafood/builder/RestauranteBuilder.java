package com.algaworks.algafood.builder;

import java.math.BigDecimal;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

import lombok.Builder;

@Builder
public class RestauranteBuilder {
	
	@Builder.Default
	private String nome = "Tempero Brasileiro";
	
	@Builder.Default
	private BigDecimal taxaFrete = new BigDecimal(10);
	
	@Builder.Default
	private Cozinha cozinha = null;
	
	public Restaurante buildRestaurante() {
		cozinha = new Cozinha();
		cozinha.setId(2L);
		cozinha.setNome("Brasileira");
		 
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(nome);
		restaurante.setTaxaFrete(taxaFrete);
		restaurante.setCozinha(cozinha);
		
		return restaurante;
	}

}
