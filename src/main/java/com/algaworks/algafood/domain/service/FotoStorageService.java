package com.algaworks.algafood.domain.service;

import java.util.UUID;

import com.algaworks.algafood.domain.model.dto.FotoRecuperada;
import com.algaworks.algafood.domain.model.dto.NovaFoto;

public interface FotoStorageService {
	
	
	void armazenar(NovaFoto novaFoto);
	
	void remover(String nomeArquivo);
	
	FotoRecuperada recuperar(String nomeArquivo);
	
	default void substituir(String nomeArquivoAntigo, NovaFoto novaFoto) {
		this.armazenar(novaFoto);
		
		if (nomeArquivoAntigo != null) {
			this.remover(nomeArquivoAntigo);
		}
	}
	
	default String gerarNovoNomeArquivo(String nomeArquivo) {
		return UUID.randomUUID() + "_" + nomeArquivo;
	}

}
