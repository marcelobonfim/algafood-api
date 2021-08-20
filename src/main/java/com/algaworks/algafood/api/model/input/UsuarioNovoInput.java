package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioNovoInput extends UsuarioUpdateInput {
	
	@NotBlank
	private String senha;

}
