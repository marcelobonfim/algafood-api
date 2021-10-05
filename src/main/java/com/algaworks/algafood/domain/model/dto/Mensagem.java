package com.algaworks.algafood.domain.model.dto;

import java.util.Map;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

@Builder
@Getter
public class Mensagem {
	
	@Singular
	private Set<String> destinatarios;
	
	@NonNull
	private String assunto;
	
	@NonNull
	private String corpo;
	
	@Singular("variavel")
	private Map<String, Object> variaveis;

}
