package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.dto.Mensagem;

public interface EnvioEmailService {
	
	void enviar(Mensagem mensagem);

}
