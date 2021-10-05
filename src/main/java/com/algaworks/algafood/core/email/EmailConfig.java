package com.algaworks.algafood.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.core.email.EmailProperties.TipoImpl;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.infrastructure.service.email.FakeEnvioEmailServiceImpl;
import com.algaworks.algafood.infrastructure.service.email.SandBoxEnvioEmailServiceImpl;
import com.algaworks.algafood.infrastructure.service.email.SmtpEnvioEmailServiceImpl;

@Configuration
public class EmailConfig {
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Bean
	public EnvioEmailService envioEmailService() {
		if (TipoImpl.SMTP.equals(emailProperties.getImpl())) {
			return new SmtpEnvioEmailServiceImpl();
		} else if (TipoImpl.SANDBOX.equals(emailProperties.getImpl())) {
			return new SandBoxEnvioEmailServiceImpl();
		} else {
			return new FakeEnvioEmailServiceImpl();
		}
	}

}
