package com.algaworks.algafood.infrastructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.algaworks.algafood.domain.model.dto.Mensagem;
import com.algaworks.algafood.domain.service.TemplateEmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class FreeMarkerTemplateEmailServiceImpl implements TemplateEmailService {
	
	@Autowired
	private Configuration freemarkerConfig;

	@Override
	public String processarTemplate(Mensagem mensagem) {
		try {
			Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());
			
			return FreeMarkerTemplateUtils.processTemplateIntoString(
					template, mensagem.getVariaveis());
		} catch (Exception e) {
			throw new EmailException("Não foi possível montar o template do e-mail", e);
		}
	}

}
