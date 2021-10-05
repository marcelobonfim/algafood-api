package com.algaworks.algafood.infrastructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;

import com.algaworks.algafood.domain.model.dto.Mensagem;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.domain.service.TemplateEmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailServiceImpl implements EnvioEmailService {
	
	@Autowired
	private TemplateEmailService templateEmailService;

	@Override
	public void enviar(Mensagem mensagem) {
		
		String corpo = templateEmailService.processarTemplate(mensagem);
		
		 log.info("[FAKE E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
	}

}
