package com.algaworks.algafood.domain.model.dto;

import java.io.InputStream;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NovaFoto {
	
	private String nomeArquivo;
	private String contentType;
	private InputStream inputStream;

}
