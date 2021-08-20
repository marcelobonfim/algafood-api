package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioSenhaInput {
	
	@NotBlank
	private String senhaAtual;
	
	@NotBlank
	private String senhaNova;

}
